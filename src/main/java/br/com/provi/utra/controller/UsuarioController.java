package br.com.provi.utra.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.provi.utra.model.Usuario;
import br.com.provi.utra.model.UsuarioLogin;
import br.com.provi.utra.service.UsuarioService;



@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> login(@RequestBody Optional<UsuarioLogin> user) {
		return usuarioService.autenticarUsuario(user)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario) {

		Optional<Usuario> usuarioResp = usuarioService.cadastrarUsuario(usuario);
		try {
			return ResponseEntity.ok(usuarioResp.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@PutMapping("/alterar")
	public ResponseEntity<Usuario> putUsuario(@RequestBody Usuario usuario) {
		Optional<Usuario> usuarioResp = usuarioService.atualizarUsuario(usuario);
		try {
			return ResponseEntity.ok(usuarioResp.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
