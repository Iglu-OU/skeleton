package ee.iglu.framework.rpc.exceptions;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class FieldError {
	private final String field; // field name
	@Setter
	private List<ValidationConstraint> validationConstraints;

	@Override
	public String toString() {
		return "FieldError{" +
				"field='" + field + '\'' +
				", validationConstraints=" + validationConstraints +
				'}';
	}
}
