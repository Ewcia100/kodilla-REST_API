package com.crud.tasks.service;

//import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {
    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail(){
        //Given

        Mail mail=new Mail("qr359789@gmail.com", TrelloService.SUBJECT, "Test message");
        MimeMessagePreparator mimeMailMessage=simpleEmailService.createMimeMessage(mail);


        //When
        try{
        javaMailSender.send(mimeMailMessage);}
        catch(Exception e){
            System.out.println(e);
        }

        //Then
        verify(javaMailSender, times(1)).send(mimeMailMessage);

    }

}