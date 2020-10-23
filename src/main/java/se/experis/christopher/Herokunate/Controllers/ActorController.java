package se.experis.christopher.Herokunate.Controllers;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import se.experis.christopher.Herokunate.Models.Actor;
import se.experis.christopher.Herokunate.Repositories.ActorRepository;

@RestController
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    Actor actor;
    HttpStatus resp;

    @GetMapping("/actor/{id}")
    public ResponseEntity<Actor> getActorById(HttpServletRequest request, @PathVariable Integer id) {

        if (actorRepository.existsById(id)) {
            System.out.println("Actor with id: " + id);
            actor = actorRepository.findById(id).get();
            resp = HttpStatus.OK;
        } else {
            System.out.println("Actor not found");
            actor = null;
            resp = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(actor, resp);
    }

    @GetMapping("/actor/all")
    public ResponseEntity<List<Actor>> getAllActors(HttpServletRequest request) {
        var actorList = actorRepository.findAll();

        HttpStatus resp = HttpStatus.OK;
        return new ResponseEntity<>(actorList, resp);
    }

    @PostMapping("/actor/add")
    public ResponseEntity<Actor> addActor(HttpServletRequest request, @RequestBody Actor actor) {

        actor = actorRepository.save(actor);

        System.out.println("New actor with id: " + actor.id);

        HttpStatus resp = HttpStatus.CREATED;

        return new ResponseEntity<>(actor, resp);
    }

    @PutMapping("/actor/replace/{id}")
    public ResponseEntity<Actor> replaceActor(HttpServletRequest request, @RequestBody Actor newActor,
            @PathVariable Integer id) {
        HttpStatus resp;

        if (actorRepository.existsById(id)) {
            Optional<Actor> actorRepo = actorRepository.findById(id);
            Actor actor = actorRepo.get();

            actor.firstName = newActor.firstName;
            actor.lastName = newActor.lastName;
            actor.dateOfBirth = newActor.dateOfBirth;
            actor.urlto_imdb_page = newActor.urlto_imdb_page;

            actorRepository.save(actor);
            resp = HttpStatus.OK;
        } else {
            resp = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(actor, resp);

    }

    @PatchMapping("/actor/update/{id}")
    public ResponseEntity<Actor> updateActor(HttpServletRequest request, @RequestBody Actor newActor,
            @PathVariable Integer id) {

        if (actorRepository.existsById(id)) {
            actor = actorRepository.findById(id).get();

            if (newActor.firstName != null) {
                actor.firstName = newActor.firstName;
            }
            if (newActor.lastName != null) {
                actor.lastName = newActor.lastName;
            }
            if (newActor.dateOfBirth != null) {
                actor.dateOfBirth = newActor.dateOfBirth;
            }
            if (newActor.urlto_imdb_page != null) {
                actor.urlto_imdb_page = newActor.urlto_imdb_page;
            }

            actorRepository.save(actor);
            System.out.println("Updated actor with id: " + actor.id);
            resp = HttpStatus.OK;
        } else {
            System.out.println("Actor not found");
            resp = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(actor, resp);
    }

    @DeleteMapping("/actor/delete/{id}")
    public ResponseEntity<String> deleteActor(HttpServletRequest request, @PathVariable Integer id) {

        String message = "";

        if (actorRepository.existsById(id)) {
            actorRepository.deleteById(id);
            System.out.println("Deleted actor with id: " + id);
            message = "SUCCESS";
            resp = HttpStatus.OK;
        } else {
            System.out.println("Actor not found with id: " + id);
            message = "Fail";
            resp = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(message, resp);
    }

}
