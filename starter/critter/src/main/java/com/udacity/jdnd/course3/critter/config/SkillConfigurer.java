package com.udacity.jdnd.course3.critter.config;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Skill;
import com.udacity.jdnd.course3.critter.model.SkillRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SkillConfigurer {

    private static final Logger log = LoggerFactory.getLogger(SkillConfigurer.class);

    private SkillRepository skillRepository;

    public SkillConfigurer(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @PostConstruct
    public void initSkills() {
        for (EmployeeSkill skill : EmployeeSkill.values()) {
            Skill skill1 = skillRepository.findBySkill(skill).orElseGet(() -> {
                return skillRepository.save(new Skill(skill));
            });
            log.info(skill1.toString());
        }
    }

}
