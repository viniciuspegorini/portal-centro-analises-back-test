package com.portal.centro.API.utils;

import com.portal.centro.API.model.Permission;
import com.portal.centro.API.enums.Type;
import org.springframework.stereotype.Component;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Component
public class UtilsService {

    //////////// PROFESSORES ////////////
    public boolean isValidProfessor(String email) {
        boolean valido = false;
        String[] dominiosValidos = {"professores.utfpr.edu.br", "utfpr.edu.br"}; //Ambos receberão email

        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate(); //Valida o formato do email usando as especificações RFC 5322
            String dominio = internetAddress.getAddress().split("@")[1]; /*Separa o endereço de email em duas partes:
                                                                                o nome do usuário e o domínio, separados
                                                                                pelo caractere "@". Acessando a segunda
                                                                                String do array com [1]  */

            for (String dominioValido : dominiosValidos) {
                if (dominio.equals(dominioValido)) {
                    valido = true;
                    break;
                }
            }

        } catch (Exception e) {
            // Endereço de email não permitido
        }

        return valido;
    }


    //////////// ALUNOS ////////////
    public boolean isValidStudent(String email) {
        boolean valido = false;
        String[] dominiosValidos = {"alunos.utfpr.edu.br"};

        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            String dominio = internetAddress.getAddress().split("@")[1];

            for (String dominioValido : dominiosValidos) {
                if (dominio.equals(dominioValido)) {
                    valido = true;
                    break;
                }
            }

        } catch (AddressException e) {
            // Endereço de email não permitido
        }

        return valido;
    }

    //////////// PARCEIROS E EXTERNOS ////////////
    public boolean isValidGeneral(String email) {

        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();

            return true;
        } catch (AddressException e) {
            return false;
        }
    }

    public Type getRoleType(String email){
        String substr = email.substring(email.indexOf("@") + 1, email.indexOf('.'));

        if("alunos".equals(substr)) {
            return Type.STUDENT;
        } else if ( "professores".equals(substr)) {
            return Type.PROFESSOR;
        }

        return Type.EXTERNAL;
    }

    public List<Permission> getPermissionsByRole(final Type role) {
        return null;
    }

}
