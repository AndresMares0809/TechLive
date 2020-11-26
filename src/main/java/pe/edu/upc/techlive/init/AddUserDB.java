package pe.edu.upc.techlive.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.upc.techlive.models.entities.Usuario;
import pe.edu.upc.techlive.models.repositories.UsuarioRepository;
import pe.edu.upc.techlive.utils.Segmento;

@Service
public class AddUserDB implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		// SOLO DESBLOQUEAR CUANDO SE REQUIERA CREAR USUARIO DE FORMA MANUAL
	
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		
		Usuario cliente1 = new Usuario();
		cliente1.setUsername("cliente1");
		cliente1.setPassword( bcpe.encode("cliente") );
		cliente1.setEnable(true);
		cliente1.setSegmento(Segmento.CLIENTE);
		cliente1.setIdSegmento(1);
		
		Usuario cliente2 = new Usuario();
		cliente2.setUsername("cliente2");
		cliente2.setPassword( bcpe.encode("cliente") );
		cliente2.setEnable(true);
		cliente2.setSegmento(Segmento.CLIENTE);
		cliente2.setIdSegmento(2);
		
		Usuario cliente3 = new Usuario();
		cliente3.setUsername("cliente3");
		cliente3.setPassword( bcpe.encode("cliente") );
		cliente3.setEnable(true);
		cliente3.setSegmento(Segmento.CLIENTE);
		cliente3.setIdSegmento(3);
		
		Usuario cliente4 = new Usuario();
		cliente4.setUsername("cliente4");
		cliente4.setPassword( bcpe.encode("cliente") );
		cliente4.setEnable(true);
		cliente4.setSegmento(Segmento.CLIENTE);
		cliente4.setIdSegmento(4);
		
		Usuario proveedor1 = new Usuario();
		proveedor1.setUsername("proveedor1");
		proveedor1.setPassword( bcpe.encode("proveedor") );
		proveedor1.setEnable(true);
		proveedor1.setSegmento(Segmento.PROVEEDOR);
		proveedor1.setIdSegmento(1);
		
		Usuario proveedor2 = new Usuario();
		proveedor2.setUsername("proveedor2");
		proveedor2.setPassword( bcpe.encode("proveedor") );
		proveedor2.setEnable(true);
		proveedor2.setSegmento(Segmento.PROVEEDOR);
		proveedor2.setIdSegmento(2);
		
		Usuario proveedor3 = new Usuario();
		proveedor3.setUsername("proveedor3");
		proveedor3.setPassword( bcpe.encode("proveedor") );
		proveedor3.setEnable(true);
		proveedor3.setSegmento(Segmento.PROVEEDOR);
		proveedor3.setIdSegmento(3);
		
		// ROLE_CUSTOMER, ROLE_PROVIDER, ROLE_ADMIN
		cliente1.addAuthority("ROLE_CUSTOMER");
		cliente2.addAuthority("ROLE_CUSTOMER");
		cliente3.addAuthority("ROLE_CUSTOMER");
		cliente4.addAuthority("ROLE_CUSTOMER");
		
		proveedor1.addAuthority("ROLE_PROVIDER");
		proveedor2.addAuthority("ROLE_PROVIDER");
		proveedor3.addAuthority("ROLE_PROVIDER");
		
		// ACCESS_
		cliente1.addAuthority("ACCESS_DESC");
		cliente2.addAuthority("ACCESS_PROMO");
		
		usuarioRepository.save(cliente1);
		usuarioRepository.save(cliente2);
		usuarioRepository.save(cliente3);
		usuarioRepository.save(cliente4);
		
		usuarioRepository.save(proveedor1);
		usuarioRepository.save(proveedor2);
		usuarioRepository.save(proveedor3);
	
		
	}

}
