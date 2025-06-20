package com.prasad.facade.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/")
public class FacadeController {

    @GetMapping("/healthcheck")
    ResponseEntity<String> healthcheck() throws InterruptedException, UnknownHostException {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        log.info("Inside FacadeController --> healthcheck @ " + timeStamp);
        String hostName = InetAddress.getLocalHost().getHostName();
        String responseText = "Host : "+hostName+ " Facade healthcheck @ " + timeStamp + " - All OK";
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body(responseText.toString());
    }
}
