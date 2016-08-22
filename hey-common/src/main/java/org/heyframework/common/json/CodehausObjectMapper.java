package org.heyframework.common.json;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;
import org.heyframework.common.util.DateUtils;

@Deprecated
public class CodehausObjectMapper extends ObjectMapper {
	public CodehausObjectMapper() {

		/**
		 * 处理reponsebody 日期转换成字符串
		 */
		CustomSerializerFactory factory = new CustomSerializerFactory();
		factory.addGenericMapping(Date.class, new JsonSerializer<Date>() {
			@Override
			public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
				String date = value.toString();
				if (date.contains(":") && !date.contains("00:00:00")) {
					jsonGenerator.writeString(DateUtils.formatDateTimeString(value));
				} else {
					jsonGenerator.writeString(DateUtils.formatDateString(value));
				}
			}
		});
		this.setSerializerFactory(factory);
	}
}
