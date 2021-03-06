package com.lavoine.escapedeath.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lavoine.escapedeath.business.PlayerBusiness;
import com.lavoine.escapedeath.model.Player;

import io.swagger.annotations.ApiOperation;

@RestController
public class PlayerController {

	@Autowired
	private PlayerBusiness business;

	@ApiOperation(value = "Fournit la liste des joueurs")
	@GetMapping(value = "/players", produces = "application/json")
	public List<Player> listerJoueurs() {
		return business.listerJoueurs();
	}

	@ApiOperation(value = "Le joueur désigné par {id} choisit de ne pas bouger")
	@PutMapping(value = "/player/{id}/stay")
	public Player nePasBouger(@PathVariable int id) {
		return business.nePasBouger(id);
	}

	@ApiOperation(value = "Le joueur désigné par {id} se cache ou sort de sa cachette")
	@PutMapping(value = "/player/{id}/togglehidden")
	public Player changerStatutCache(@PathVariable int id) {
		return business.changerStatutCache(id);
	}

	@ApiOperation(value = "Le joueur désigné par {id} se se déplace vers la salle désignée par {roomId}")
	@PutMapping(value = "/player/{id}/move/{roomId}")
	public Player seDeplacer(@PathVariable int id, @PathVariable int roomId) {
		return business.seDeplacer(id, roomId);
	}

	@ApiOperation(value = "Le joueur désigné par {id} meurt")
	@PutMapping(value = "/player/{id}/kill")
	public Player tuerJoueur(@PathVariable int id) {
		return business.tuerJoueur(id);
	}

	@ApiOperation(value = "Tour suivant : les joueurs sont tous configurés comme n'ayant pas joué leur tour,"
			+ " renvoie le nombre de joueurs encore en vie")
	@PutMapping(value = "/players/nextturn")
	public int tourSuivant() {
		return business.tourSuivant();
	}

	@ApiOperation(value = "Indique si les joueurs ont fini de jouer leur tour")
	@GetMapping(value = "/players/endofturn")
	public boolean tourFini() {
		return business.tourFini();
	}
}
