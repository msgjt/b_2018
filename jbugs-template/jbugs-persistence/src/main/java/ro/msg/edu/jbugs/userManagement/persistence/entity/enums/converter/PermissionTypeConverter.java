package ro.msg.edu.jbugs.userManagement.persistence.entity.enums.converter;


import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.PermissionType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PermissionTypeConverter implements AttributeConverter<PermissionType, String> {

    @Override
    public String convertToDatabaseColumn(PermissionType permissionType) {
        return permissionType.name();
    }

    @Override
    public PermissionType convertToEntityAttribute(String s) {
        switch (s) {
            case "PERMISSION_MANAGEMENT":
                return PermissionType.PERMISSION_MANAGEMENT;

            case "USER_MANAGEMENT":
                return PermissionType.USER_MANAGEMENT;

            case "BUG_MANAGEMENT":
                return PermissionType.BUG_MANAGEMENT;

            case "BUG_CLOSE":
                return PermissionType.BUG_CLOSE;

            case "BUG_EXPORT_PDF":
                return PermissionType.BUG_EXPORT_PDF;

            default:
                throw new IllegalArgumentException("ShortName [" + s
                        + "] not supported.");
        }
    }
}
