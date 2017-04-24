package ee.iglu.framework.rpc.exceptions;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationConstraint {
	private final String code; // constraint code
	private final Map<String, Object> args; // constraint args

	@Override
	public String toString() {
		return "ValidationConstraint{" +
				"code='" + code + '\'' +
				", args=" + args +
				'}';
	}
}
