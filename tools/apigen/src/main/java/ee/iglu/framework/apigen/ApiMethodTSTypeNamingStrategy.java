package ee.iglu.framework.apigen;

import ee.iglu.skeleton.rpc.RpcMethod;
import java2typescript.jackson.module.conf.typename.WithEnclosingClassTSTypeNamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

class ApiMethodTSTypeNamingStrategy extends WithEnclosingClassTSTypeNamingStrategy {

	@Override
	public String getName(Class<?> rawClass) {
		String classNameWithEnclosingClasses = super.getName(rawClass);
		if (isEnclosingClassApiMethod(rawClass)) {
			return getClassNamePrefixFromPackage(rawClass) + "_" + classNameWithEnclosingClasses;
		}
		return classNameWithEnclosingClasses;
	}

	private String getClassNamePrefixFromPackage(Class<?> rawClass) {
		String classNamePrefixFromPackage = getPackageNameForPrefix(rawClass);
		return StringUtils.capitalize(classNamePrefixFromPackage);
	}

	String getPackageNameForPrefix(Class<?> rawClass) {
		Package aPackage = rawClass.getPackage();
		return getPackageNameForPrefix(aPackage);
	}

	String getPackageNameForPrefix(Package aPackage) {
		String packageName = aPackage.getName();
		String packageNameWithoutApiSufix = packageName;
		if (packageName.endsWith(".api")) {
			packageNameWithoutApiSufix = getParentPackage(packageName);
		}
		return StringUtils.substringAfterLast(packageNameWithoutApiSufix, ".");
	}

	private String getParentPackage(String packageName) {
		return StringUtils.substringBeforeLast(packageName, ".");
	}

	boolean isEnclosingClassApiMethod(Class<?> klass) {
		Class<?> enclosingClass = klass.getEnclosingClass();
		if (enclosingClass == null) {
			return false;
		}
		return Arrays.stream(enclosingClass.getInterfaces())
				.filter((Class<?> interf) -> interf.equals(RpcMethod.class))
				.findFirst()
				.isPresent();
	}

}
