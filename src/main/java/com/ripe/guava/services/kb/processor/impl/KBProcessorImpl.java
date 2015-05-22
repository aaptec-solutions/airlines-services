package com.ripe.guava.services.kb.processor.impl;


import org.drools.core.common.DefaultFactHandle;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message.Level;
import org.kie.api.io.KieResources;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.aaptec.dgca.model.FDTLModel;
import com.ripe.guava.services.kb.processor.KBProcessor;

public class KBProcessorImpl implements KBProcessor{

	public Object fireRules(Object arg, String rulePath) {
		FDTLModel model = new FDTLModel();
		model.setCrewType("abc");
		//model.setFlightTime(new FDTLTime(20,0,0,new Long(0)));
		
		String[] rules = { "com/aaptec/dgca/pilot_fdtl.drl" };
        Object[] facts = { model };
        
        this.buildKnowledgeBase(rules, facts);
        
        return model;
		
	}
	
	private void buildKnowledgeBase(String[] rules, Object[] facts){
		
		KieServices kieServices = KieServices.Factory.get();
        KieResources kieResources = kieServices.getResources();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        KieRepository kieRepository = kieServices.getRepository();
  
        for(String ruleFile : rules)
        {
            Resource resource = kieResources.newClassPathResource(ruleFile);
            kieFileSystem.write("src/main/resources/"+ruleFile, resource);
        }
  
        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
  
        kb.buildAll();
  
        if (kb.getResults().hasMessages(Level.ERROR))
        {
            throw new RuntimeException("Build Errors:\n" + kb.getResults().toString());
        }
  
        KieContainer kContainer = kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
 
        KieSession kSession = kContainer.newKieSession();
         
       
        FactHandle applicantFactHandle = kSession.insert(facts[0]);        
  
       kSession.fireAllRules();
       
     //get the processed data out of the rule engine
     		FDTLModel model = (FDTLModel) ((DefaultFactHandle)applicantFactHandle).getObject();
     		kSession.dispose();
     		
     		System.out.println(model.getViolationText());

		
	}

}
