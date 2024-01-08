package com.api.neki.services;

import com.api.neki.dto.SkillsDTO;
import com.api.neki.entities.LevelSkills;
import com.api.neki.entities.Skills;
import com.api.neki.entities.User;
import com.api.neki.entities.exceptions.NekiException;
import com.api.neki.repository.LevelSkillsRepository;
import com.api.neki.repository.SkillsRepository;
import com.api.neki.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillService {

    @Autowired
    private SkillsRepository skillsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LevelSkillsRepository levelSkillsRepository;

    @Autowired
    private ModelMapper mapper;

    public List<SkillsDTO> listSkillsByUserId(Long idUser) {
        Optional<List<Skills>> optionalSkillsList = skillsRepository.findByUserId(idUser);

        if (optionalSkillsList.isEmpty()) {
            throw new NekiException("Nenhuma skill encontrada para o usuário de Id: " + idUser);
        }

        List<SkillsDTO> skillsDTOList = optionalSkillsList.get()
                .stream()
                .map(skill -> mapper.map(skill, SkillsDTO.class))
                .collect(Collectors.toList());

        return skillsDTOList;
    }


    public SkillsDTO cadastrarSkillsPorUsuarioId(Long id, SkillsDTO skillDTO) {
        User usuario = userRepository.findById(id)
                .orElseThrow(() -> new NekiException("Nenhum registro encontrado para o ID: " + id));

        LevelSkills level = obterOuCriarLevel(skillDTO.getLevel());

        Skills skillModel = mapper.map(skillDTO, Skills.class);
        skillModel.setUser(usuario);
        skillModel.setLevel(level);
        skillModel = skillsRepository.save(skillModel);

        return mapper.map(skillModel, SkillsDTO.class);
    }

    private LevelSkills obterOuCriarLevel(String levelName) {
        return levelSkillsRepository.findByLevelSkill(levelName)
                .orElseGet(() -> {
                    LevelSkills newLevel = new LevelSkills(levelName);
                    return levelSkillsRepository.save(newLevel);
                });
    }


    public SkillsDTO alterarLevelSkills(Long id, String level) {
        Skills skill = skillsRepository.findById(id)
                .orElseThrow(() -> new NekiException("Nenhum registro encontrado para a Skill: " + id));
        LevelSkills levelsk = obterOuCriarLevel(level);
        skill.setLevel(levelsk);
        skill = skillsRepository.save(skill);

        return mapper.map(skill, SkillsDTO.class);
    }

    public void deletarSkill(Long id) {
        skillsRepository.findById(id)
                .orElseThrow(() -> new NekiException("Nenhum registro encontrado para a Skill: " + id));
        skillsRepository.deleteById(id);
    }
}
