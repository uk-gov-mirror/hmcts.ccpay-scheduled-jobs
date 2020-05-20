package uk.gov.hmcts.payment.processors;

import static java.util.Objects.requireNonNull;

/**
 * Only exists to make testing easier
 */
public class EnvironmentVariableRetriever {

    public String get(String environmentVariable) {
        return get(environmentVariable, null);
    }
    
    public String get(String environmentVariable, String defaultValue) {
        String variable = System.getenv(environmentVariable);
        
        if (defaultValue == null) {
            requireNonNull(variable, environmentVariable + " environment variable is required");
        }
        
        if (variable == null) {
            return defaultValue;
        }
        
        return variable;
    }
}
