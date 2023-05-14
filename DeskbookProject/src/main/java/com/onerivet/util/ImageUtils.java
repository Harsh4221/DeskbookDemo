package com.onerivet.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class ImageUtils {

	public String encodeImage(String path) throws Exception {
		File file = new File(path);
		byte[] data = FileUtils.readFileToByteArray(file);

		String imageString = Base64.getEncoder().encodeToString(data);
		
		return imageString;
	}

	public void decodeImage(String image, String path) throws Exception {

		System.out.println(image);

		byte[] data = Base64.getDecoder().decode(image);

		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		BufferedImage image1 = ImageIO.read(bis);

		int size = data.length;
		if (size > (2 * 1024 * 1024)) {
			throw new IllegalArgumentException(
					"File size exceeds the maximum limit of 2MB. Please choose a smaller file");
		}

		if (image1 != null && image1.getWidth() > 0 && image1.getHeight() > 0) {
			FileOutputStream img = new FileOutputStream(path);
			img.write(data);
			img.close();
		}
	}
}
