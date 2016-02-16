/**
 * 
 */
package com.jinjiang.hotel.validators;

import org.zkoss.bind.ValidationContext;
import org.zkoss.util.resource.Labels;

/**
 * @author gaojianm
 *
 */
public class StringLengthValidator extends GenericStringValidator {

	/**
	 * 
	 */
	public StringLengthValidator() {
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.validators.GenericStringValidator#validate(org.zkoss.bind.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext ctx) {
		super.validate(ctx);
		int minLength=config.getInt("validation.passwordMinLength");
		if (value.length()<minLength) {
			addInvalidMessage(ctx, Labels.getLabel("msg.string.too.short", new Object[]{minLength}));
		}
	}

}
