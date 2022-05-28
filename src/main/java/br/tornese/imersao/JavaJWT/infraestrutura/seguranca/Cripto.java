package br.tornese.imersao.JavaJWT.infraestrutura.seguranca;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import br.tornese.imersao.JavaJWT.domain.seguranca.ICripto;

public class Cripto implements ICripto {

    @Override
    public String build(String senha) throws Exception {
        String strMinhaChave = "123456789 123456789 123456789 12"; 

		Key minhaChave = new SecretKeySpec(strMinhaChave.getBytes(), "AES");

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, minhaChave);
		cipher.update(senha.getBytes());

		String stringCriptografada = new String(cipher.doFinal(), "UTF-8");				
		byte[] teste = stringCriptografada.getBytes();
		StringBuilder cryptoHex = new StringBuilder();
		for (byte b: teste) {
			cryptoHex.append(Integer.toHexString(b));
		}
		return cryptoHex.toString();
    }
    
}
