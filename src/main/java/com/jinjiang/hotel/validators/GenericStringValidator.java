/**
 * 
 */
package com.jinjiang.hotel.validators;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.resource.Labels;

import com.jinjiang.hotel.config.AppConfig;

/**
 * @author gaojianm
 *
 */
public class GenericStringValidator extends AbstractValidator {
	protected AppConfig config=AppConfig.getInstance();
	protected String value;
	protected Boolean isValid;
	/**
	 * 
	 */
	public GenericStringValidator() {
	}

	/* (non-Javadoc)
	 * @see org.zkoss.bind.Validator#validate(org.zkoss.bind.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext ctx) {
		value=(String) ctx.getProperty().getValue();
		if (isNullOrEmpty(value)) {
			addInvalidMessage(ctx, Labels.getLabel("msg.not.empty"));
			isValid=false;
			return;
		}
		isValid=true;
	}

	protected boolean isNullOrEmpty(String val) {
		return val==null||val.trim().isEmpty();
	}
}
