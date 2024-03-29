package com.intelliSwift.proKabaddi.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.stereotype.Component;


@Component("DateTimeConverter")
@Converter(autoApply = true)
public class DateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp>
{
	  
	  @Override
	  public Timestamp convertToDatabaseColumn ( LocalDateTime locDateTime )
	  {
	    return (locDateTime == null ? null : Timestamp.valueOf(locDateTime));
	  }
	  
	  @Override
	  public LocalDateTime convertToEntityAttribute ( Timestamp sqlTimestamp )
	  {
	    return (sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime());
	  }
}