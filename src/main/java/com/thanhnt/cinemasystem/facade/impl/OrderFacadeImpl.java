package com.thanhnt.cinemasystem.facade.impl;

import com.thanhnt.cinemasystem.entity.*;
import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.exception.LoginException;
import com.thanhnt.cinemasystem.exception.MovieTimeException;
import com.thanhnt.cinemasystem.exception.SeatException;
import com.thanhnt.cinemasystem.facade.DiscountService;
import com.thanhnt.cinemasystem.facade.OrderFacade;
import com.thanhnt.cinemasystem.request.OrderRequest;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.OrderDetailResponse;
import com.thanhnt.cinemasystem.security.SecurityUserDetails;
import com.thanhnt.cinemasystem.service.MovieTimeService;
import com.thanhnt.cinemasystem.service.OrderService;
import com.thanhnt.cinemasystem.service.SeatService;
import com.thanhnt.cinemasystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderFacadeImpl implements OrderFacade {
  private final OrderService orderService;
  private final UserService userService;
  private final MovieTimeService movieTimeService;
  private final DiscountService discountService;
  private final SeatService seatService;

  @Override
  public BaseResponse<Void> createOrder(OrderRequest request) {
    var userPrinciple =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user =
        userService
            .findByEmail(userPrinciple.getEmail())
            .orElseThrow(() -> new LoginException(ErrorCode.USER_NOT_FOUND));

    MovieTime movieTime =
        movieTimeService
            .findById(request.getShowtimeId())
            .orElseThrow(() -> new MovieTimeException(ErrorCode.MOVIE_TIME_NOT_FOUND));

    var ticketOrder =
        orderService.findOrderBySeatIdAndShowTimeId(request.getSeatId(), request.getShowtimeId());
    if (ticketOrder != null) throw new SeatException(ErrorCode.SEAT_HAS_BOOKED);

    Seat seat = seatService.findSeatById(request.getSeatId());

    float discountPrice = 0;
    Discount discount = null;
    if (request.getDiscountCode() != null) {
      discount = discountService.findDiscountByCode(request.getDiscountCode());
      boolean isDiscountPriceMoreThanMaxDiscountPrice =
          seat.getPrice() * discount.getDiscountPercentage() > discount.getMaxDiscountAmount();
      discountPrice =
          (isDiscountPriceMoreThanMaxDiscountPrice)
              ? discount.getMaxDiscountAmount()
              : seat.getPrice() * discount.getDiscountPercentage();
      discount.updateQuantity();
      discountService.saveDiscount(discount);
    }
    float totalPrice = seat.getPrice() - discountPrice;

    orderService.saveOrder(
        TicketOrder.builder()
            .user(user)
            .movieTime(movieTime)
            .discount(discount)
            .price(totalPrice)
            .isPayment(false)
            .discountAmount(discountPrice)
            .seat(seat)
            .build());

    return BaseResponse.ok();
  }

  @Override
  public BaseResponse<OrderDetailResponse> getOrderDetail(Long orderId) {
    TicketOrder ticketOrder = orderService.findById(orderId);
    String cinemaAddress =
        ticketOrder.getMovieTime().getCinemaScreen().getProvince().getProvinceName()
            + " - "
            + ticketOrder.getMovieTime().getCinemaScreen().getDistrict().getDistrictName()
            + " - "
            + ticketOrder.getMovieTime().getCinemaScreen().getWard().getWardName();
    return BaseResponse.build(
        OrderDetailResponse.builder()
            .cinemaId(ticketOrder.getMovieTime().getCinemaScreen().getId())
            .cinemaName(ticketOrder.getMovieTime().getCinemaScreen().getCinemaName())
            .cinemaAddress(cinemaAddress)
            .movieId(ticketOrder.getMovieTime().getMovieTime().getId())
            .movieName(ticketOrder.getMovieTime().getMovieTime().getName())
            .roomCode(ticketOrder.getMovieTime().getRoomScreen().getRoomCode())
            .seatType(ticketOrder.getSeat().getSeatType())
            .seatCode(ticketOrder.getSeat().getSeatCode())
            .showTime(ticketOrder.getMovieTime().getShowtime())
            .price(ticketOrder.getPrice())
            .build(),
        true);
  }
}
