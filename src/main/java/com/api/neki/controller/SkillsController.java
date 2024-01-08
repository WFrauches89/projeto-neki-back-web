package com.api.neki.controller;

import com.api.neki.dto.SkillsDTO;
import com.api.neki.entities.exceptions.NekiException;
import com.api.neki.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/skills")
public class SkillsController {
    @Autowired
    private SkillService skillService;

    @GetMapping("/listarSkills/usuario/{id}")
    @PreAuthorize("hasAuthority('NORMAL')")
    public ResponseEntity<List<SkillsDTO>> listarSkills(@PathVariable Long id, HttpServletResponse response){
        return ResponseEntity.ok(skillService.listSkillsByUserId(id));
    }

    @PostMapping("/cadastrarSkills/usuario/{id}")
    @PreAuthorize("hasAuthority('NORMAL')")
    public ResponseEntity<SkillsDTO> cadastrarSkills(@PathVariable Long id, @RequestBody SkillsDTO skill){
        if(skill.getNameSkill() == null || skill.getNameSkill().length() < 1){
            throw new NekiException("Nome da skill não pode estar vazio.");
        }
        if(skill.getLevel() == null){
            throw new NekiException("Level da skill não pode estar vazio.");
        }
        if(skill.getDescription() == null){
            throw new NekiException("Descrição da skill não pode estar vazio.");
        }
        return ResponseEntity.status(201).body(skillService.cadastrarSkillsPorUsuarioId(id, skill));
    }

    @PostMapping("alterarLevel/Skill/{id}")
    @PreAuthorize("hasAuthority('NORMAL')")
    public ResponseEntity<SkillsDTO> alterarLevelSkills(@PathVariable Long id, @RequestBody SkillsDTO skill){
        if(skill.getLevel() == null ){
            throw new NekiException("Level da skill não pode estar vazio.");
        }
        return ResponseEntity.status(200).body(skillService.alterarLevelSkills(id, skill.getLevel()));
    }

    @GetMapping("usuario/{idUser}/Skill/{idSkill}")
    @PreAuthorize("hasAuthority('NORMAL')")
    public ResponseEntity<SkillsDTO> obterSkill(@PathVariable Long idUser, @PathVariable Long idSkill){
        if(idUser == null ){
            throw new NekiException("O id do usuário não pode estar vazio.");
        }
        if(idSkill == null ){
            throw new NekiException("O id da skill não pode estar vazio.");
        }
        return ResponseEntity.status(200).body(skillService.findSkillBy(idUser,idSkill));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('NORMAL')")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        skillService.deletarSkill(id);
        return ResponseEntity.status(204).build();
    }
}
