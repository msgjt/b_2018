package ro.msg.edu.jbugs.userManagement.business.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessExceptionCode;
import ro.msg.edu.jbugs.userManagement.persistence.entity.*;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.PermissionType;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.RoleType;

import java.security.Key;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtManager {

    private static JwtManager instance;

    public static JwtManager getInstance() {
        if (instance == null) {
            instance = new JwtManager();
        }
        return instance;
    }

    private static final String CLAIM_ROLES = "roles";
    private static final String CLAIM_PERMISSIONS = "permissions";

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private static final Key SECRET_KEY = Keys.secretKeyFor(SIGNATURE_ALGORITHM);


    public String createToken(User user) {

        Set<RoleType> roles = user.getRoles().stream()
                .map(Role::getType)
                .collect(Collectors.toSet());

        Set<PermissionType> permissions = user.getRoles().stream()
                .map(Role::getPermissions)
                .flatMap(Set::stream)
                .map(Permission::getType)
                .collect(Collectors.toSet());

        String jws = Jwts.builder().setSubject(user.getUsername())
//                .addClaims(map)
                .claim(CLAIM_ROLES, roles)
                .claim(CLAIM_PERMISSIONS, permissions)
                .signWith(SECRET_KEY).compact();
        return jws;
    }

    public Jws<Claims> parseToken(final String token) throws BusinessException {
        try {
            Jws<Claims> parsedToken = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);
            return parsedToken;
        } catch (JwtException ex) {
            throw new BusinessException(BusinessExceptionCode.INVALID_TOKEN);
        }
    }
}

