package com.ccil.vms.util;
 
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.aspectj.apache.bcel.classfile.Method;
import org.springframework.stereotype.Component;
 
/**
 * This program demonstrates how to resize an image.
 *
 * @author www.codejava.net
 *
 */

@Component
public class ImageResizer {
 
	  /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     * @throws IOException
     */
    public void resize(File inputImageFile,
            String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        BufferedImage inputImage = ImageIO.read(inputImageFile);
 
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }
 
    /**
     * Resizes an image by a percentage of original size (proportional).
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param percent a double number specifies percentage of the output image
     * over the input image.
     * @throws IOException
     */
    public void resize(File inputImageFile,
            String outputImagePath, double percent) throws IOException {
       
        BufferedImage inputImage = ImageIO.read(inputImageFile);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        resize(inputImageFile, outputImagePath, scaledWidth, scaledHeight);
    }
 
  	
    	public byte[] resizeImageAsJPG(byte[] pImageData, int pMaxWidth) throws IOException {
    		// Create an ImageIcon from the image data
    		ImageIcon imageIcon = new ImageIcon(pImageData);
    		int width = imageIcon.getIconWidth();
    		int height = imageIcon.getIconHeight();
    	//	mLog.info("imageIcon width: #0  height: #1", width, height);
    		// If the image is larger than the max width, we need to resize it
    		if (pMaxWidth > 0 && width > pMaxWidth) {
    		    // Determine the shrink ratio
    		    double ratio = (double) pMaxWidth / imageIcon.getIconWidth();
    		    height = (int) (imageIcon.getIconHeight() * ratio);
    		    width = pMaxWidth;
    		}
    		// Create a new empty image buffer to "draw" the resized image into
    		BufferedImage bufferedResizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    		// Create a Graphics object to do the "drawing"
    		Graphics2D g2d = bufferedResizedImage.createGraphics();
    		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    		// Draw the resized image
    		g2d.drawImage(imageIcon.getImage(), 0, 0, width, height, null);
    		g2d.dispose();
    		// Now our buffered image is ready
    		// Encode it as a JPEG
    		ByteArrayOutputStream encoderOutputStream = new ByteArrayOutputStream();
    		//JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(encoderOutputStream);
    		//encoder.encode(bufferedResizedImage);
    		byte[] resizedImageByteArray = encoderOutputStream.toByteArray();
    		return resizedImageByteArray;
    	    }
    	
    	
    
    
    
    
    /**
     * Test resizing images
     */
  
 
}
