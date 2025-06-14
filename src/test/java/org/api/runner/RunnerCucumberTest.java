package org.api.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src\\test\\resources\\org\\api\\features\\IncidentManagement.feature",
				 glue = "org.api.incidentmanagementsteps", 
				 publish = true,
				 monochrome = true)
				// tags = "@Smoke and @Sanity")
				//tags = "@Smoke or @Sanity"
public class RunnerCucumberTest  extends AbstractTestNGCucumberTests{

}
