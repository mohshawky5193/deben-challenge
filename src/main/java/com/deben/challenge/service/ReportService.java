package com.deben.challenge.service;

import com.deben.challenge.model.WeatherData;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
  public  String generateReport(String city, WeatherData weatherData) {

    String fileName = "WeatherReport-"+ city+".pdf";
    try (PDDocument document = new PDDocument()) {
      PDPage page = new PDPage();
      document.addPage(page);

      try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
        contentStream.beginText();
        contentStream.newLineAtOffset(100, 700);
        contentStream.setFont(new PDType1Font(FontName.HELVETICA), 20);
        contentStream.showText("Weather Report");
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(100, 650);
        contentStream.setFont(new PDType1Font(FontName.HELVETICA), 20);
        contentStream.showText("City: " + city);
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(100, 630);
        contentStream.setFont(new PDType1Font(FontName.HELVETICA), 20);
        contentStream.showText("Description: " + weatherData.getWeather().get(0).getDescription());
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(100, 610);
        contentStream.setFont(new PDType1Font(FontName.HELVETICA), 20);
        contentStream.showText("Current Temperature: " + weatherData.getMain().getTemp() + "°C");
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(100, 590);
        contentStream.setFont(new PDType1Font(FontName.HELVETICA), 20);
        contentStream.showText("Max Temperature: " + weatherData.getMain().getTempMax() + "°C");
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(100, 570);
        contentStream.setFont(new PDType1Font(FontName.HELVETICA), 20);
        contentStream.showText("Min Temperature: " + weatherData.getMain().getTempMin() + "°C");
        contentStream.endText();
      }

      document.save(fileName);
      return fileName;
    } catch (IOException e) {
      System.err.println("Exception while trying to create pdf document - " + e);
    }
    return null;
  }
}
