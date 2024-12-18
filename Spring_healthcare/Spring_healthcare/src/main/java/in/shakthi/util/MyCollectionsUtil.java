package in.shakthi.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MyCollectionsUtil {

	public static Map<Long, String> convertToMap(List<Object[]> list) {
		// java 8 stream Api
		// 1.the Stream API is used to process collections of objects.
		// 2. A stream is a sequence of objects that supports various methods
		// which can be pipelined to produce the desired result.
		// 3.collect: The collect method is used to return the result of the
		// intermediate operations performed on the stream.

		Map<Long, String> map = list.stream().collect(Collectors.toMap(

				ob -> Long.valueOf(ob[0].toString()), ob -> ob[1].toString()

		));

		return map;

	}
	
	
	
	public static Map<Long, String> convertToMapIndex(List<Object[]> list) {
		//Java 8 Stream API
		Map<Long,String> map =
				list
				.stream()
				.collect(Collectors.toMap(
						ob->Long.valueOf(
								ob[0].toString()), 
						ob->ob[1].toString()+" "+ob[2].toString()));
		return map;
	}

}
