package com.portal.centro.API.utils;

import com.portal.centro.API.model.Permission;
import com.portal.centro.API.enums.Type;
import org.springframework.stereotype.Component;
import javax.mail.internet.InternetAddress;
import java.util.*;

@Component
public class UtilsService {

    public Type getRoleType(String email){

        String[] dominiosValidos = {"professores.utfpr.edu.br", "utfpr.edu.br", "alunos.utfpr.edu.br"}; //Permitidos

        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate(); //Valida o formato do email usando as especificações RFC 5322
            String dominio = internetAddress.getAddress().split("@")[1]; /*Separa o endereço de email em duas partes:
                                                                                o nome do usuário e o domínio, separados
                                                                                pelo caractere "@". Acessando a segunda
                                                                                String do array com [1]  */
            for (String dominioValido : dominiosValidos) {
                if (dominio.equals(dominioValido)) {
                    if ("professores.utfpr.edu.br".equals(dominio) || "utfpr.edu.br".equals(dominio)){
                        return Type.PROFESSOR;
                    }  else {
                       return Type.STUDENT;
                    }
                }
            }

        } catch (Exception e) {
            return Type.EXTERNAL;
        }

        return Type.EXTERNAL;
    }

    public Set<Permission> getPermissionsByRole(final Type role) {




        return null;
    }

}
