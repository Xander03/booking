package com.korzunva.repository.impl;

import com.korzunva.common.injector.Component;
import com.korzunva.model.Role;
import com.korzunva.repository.AbstractDAO;
import com.korzunva.repository.RoleDAO;
import com.korzunva.repository.exception.DAOException;
import com.korzunva.repository.sql.RoleSQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoleDAOImpl extends AbstractDAO<Role> implements RoleDAO {

    @Override
    public List<Role> getUserRoles(String userId) throws DAOException {
        ResultSet rs = null;
        try (PreparedStatement statement = getPreparedStatement(RoleSQL.GET_USER_ROLES)) {
            statement.setString(1, userId);
            rs = statement.executeQuery();
            List<Role> roles = new ArrayList<>();
            while (rs.next()) {
                roles.add(fromRow(rs));
            }
            return roles;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            closeResultSet(rs);
        }
    }

    @Override
    public Role addUserRole(String userId, Role role) throws DAOException {
        try (PreparedStatement statement = getPreparedStatement(RoleSQL.ADD_USER_ROLE)) {
            statement.setString(1, userId);
            statement.setLong(2, role.getId());

            statement.execute();

            return role;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    protected Role fromRow(ResultSet rs) throws DAOException {
        try {
            String roleName = rs.getString(RoleSQL.NAME);
            return Role.getByValue(roleName);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

}
