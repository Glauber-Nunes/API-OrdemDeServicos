package com.gnsoftware.Ordem.Servico.services.impl;

import com.gnsoftware.Ordem.Servico.dto.OSForm;
import com.gnsoftware.Ordem.Servico.model.Cliente;
import com.gnsoftware.Ordem.Servico.model.OS;
import com.gnsoftware.Ordem.Servico.services.EmailService;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String remetenteproperties;
    @Autowired
    private Configuration configuration;

    @Override
    public void enviarEmailOSAberta(Cliente cliente, OSForm osForm) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        Map<String, Object> propriedades = new HashMap<>();
        propriedades.put("nome", cliente.getNome()); // remetente
        propriedades.put("mensagem1", " SEU SERVIÇO FOI ABERTO COM SUCESSO, EM BREVE VOÇE RECEBERA UM EMAIL QUANDO SEU SERVIÇO FOR CONCLUIDO :)");
        propriedades.put("mensagem2", " Descrição Do Serviço: " + osForm.getDescricao());
        propriedades.put("mensagem3", " Valor Do Serviço: " + osForm.getValorTotalOrdem());

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject("Informaçoes do seu serviço");
            mimeMessageHelper.setFrom(remetenteproperties);
            mimeMessageHelper.setTo(cliente.getEmail());

            mimeMessageHelper.setText(this.getConteudoTemplate(propriedades), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void enviarEmailServicoFinalizado(OS os) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        Map<String, Object> propriedades = new HashMap<>();
        propriedades.put("nome", os.getCliente().getNome());
        propriedades.put("mensagem1", " Seu Serviço Foi finalizado Com Sucesso");
        propriedades.put("mensagem2", "");
        propriedades.put("mensagem3", " VOLTE SEMPRE");

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject("Serviço Finalizado Com Sucesso"); // titulo do email
            mimeMessageHelper.setFrom(remetenteproperties);
            mimeMessageHelper.setTo(os.getCliente().getEmail());

            mimeMessageHelper.setText(this.getConteudoTemplate(propriedades), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    private String getConteudoTemplate(Map<String, Object> model) {
        StringBuffer content = new StringBuffer();

        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("email-flth"), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }


}
