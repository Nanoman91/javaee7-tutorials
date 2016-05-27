package com.solt.jsf;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.faces.flow.Flow;
import javax.faces.flow.builder.FlowBuilder;
import javax.faces.flow.builder.FlowBuilderParameter;
import javax.faces.flow.builder.FlowDefinition;

public class RegistFlow implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Produces
	@FlowDefinition
	public Flow defineFlow(@FlowBuilderParameter FlowBuilder flowBuilder) {
		String flowId = "regist";
		flowBuilder.id("", flowId);
		flowBuilder.returnNode("success").fromOutcome("/index");
		return flowBuilder.getFlow();
	}

}
