package com.jdc.sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class DaoGenerator {
	
	public static void main(String[] args) throws IOException {
		
		Files.readAllLines(Paths.get("entity.txt"))
			.forEach(DaoGenerator::createFile);
		
	}
	
	public static void createFile(String entity) {
		try {
			List<String> lines = Files.readAllLines(Paths.get("template.txt"));
			
			List<String> result = lines.stream().map(a -> {
				if(a.contains("ENTITY")) {
					return a.replaceAll("ENTITY", entity);
				}
				return a;
			}).collect(Collectors.toList());
			
			Path path = Paths.get("result", String.format("%sDao.java", entity));
			Files.write(path, result, StandardOpenOption.CREATE);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
