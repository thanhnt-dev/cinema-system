package com.thanhnt.cinemasystem.helper;

import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.exception.ImportException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVHelper<T> {
  public static String TYPE = "text/csv";

  private final Function<CSVRecord, T> recordTFunction;

  public CSVHelper(Function<CSVRecord, T> recordTFunction) {
    this.recordTFunction = recordTFunction;
  }

  public List<T> parceCsv(InputStream inputStream) {
    try (BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        CSVParser csvParser =
            new CSVParser(
                bufferedReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()); ) {
      List<T> items = new ArrayList<>();
      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
        T item = recordTFunction.apply(csvRecord);
        items.add(item);
      }
      return items;
    } catch (IOException e) {
      throw new ImportException(ErrorCode.IMPORT_LOCATION_ERROR);
    }
  }
}
