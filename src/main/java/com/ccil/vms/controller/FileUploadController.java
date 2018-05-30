package com.ccil.vms.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ccil.vms.service.UserIdentityService;
import com.ccil.vms.util.ImageResizeUtil;
import com.ccil.vms.util.ImageResizer;

@Controller
public class FileUploadController {
  
	@Autowired
	private UserIdentityService		userIdentityService;
	@Autowired
	private ImageResizeUtil imageResizeUtil;
  // The Environment object will be used to read parameters from the 
  // application.properties configuration file
  @Autowired
  private Environment env;
  
  @RequestMapping(value = "/UpdateProfilePic", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> uploadFile(
      @RequestParam("profilePic") MultipartFile uploadfile) {
    try {
      // Get the filename and build the local file path
    	String extn =uploadfile.getOriginalFilename().substring(uploadfile.getOriginalFilename().lastIndexOf("."));
      String filename_profile_pic = userIdentityService.getPrincipal()+"_profile-widget-avatar-pic"+extn;
      String filename_profile_icon = userIdentityService.getPrincipal()+"_profile-widget-avatar-icon"+extn;

      String directory = System.getProperty("user.dir")+env.getProperty("netgloo.paths.uploadedFiles");
      String filepath_profile_pic = Paths.get(directory, filename_profile_pic).toString();
      String filepath_profile_icon = Paths.get(directory, filename_profile_icon).toString();
      
      File inputFile =
    		  new File(filepath_profile_pic);
      uploadfile.transferTo(inputFile);
     // imageResizer.resizeImageAsJPG(uploadfile.getBytes(),75);
          
//      BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath_profile_pic)));
//      stream.write(imageResizer.resizeImageAsJPG(uploadfile.getBytes(),100));
//      stream.close();
//      
//      
//      BufferedOutputStream stream1 = new BufferedOutputStream(new FileOutputStream(new File(filepath_profile_icon)));
//      stream1.write(imageResizer.resizeImageAsJPG(uploadfile.getBytes(),30));
//      stream1.close();
      
      
      // Save the file locally
    // imageResizer.resize( inputFile, filepath_profile_pic, 200,200); 
     //imageResizer.resize(inputFile, filepath_profile_icon, 75,75); 
     
     imageResizeUtil.reSizeAndSaveImage(inputFile, 400,400, filepath_profile_pic, "png");
     imageResizeUtil.reSizeAndSaveImage(inputFile, 40,40, filepath_profile_icon, "png");

    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    return new ResponseEntity<>(HttpStatus.OK);
  } // method uploadFile

} // class MainController

 
