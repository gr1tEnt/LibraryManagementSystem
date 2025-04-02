package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.model.Member;
import com.gr1tEnt.librarymanagementsystem.model.MembershipType;

import java.sql.*;
import java.util.UUID;

public class MemberService {
    private final Connection conn;

    public MemberService(Connection conn) {
        this.conn = conn;
    }

    public boolean addMember(Member member) {
        String sql = "INSERT INTO members (member_id, name, contact_info, membership_type, join_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, member.getMemberId().toString());
            stmt.setString(2, member.getName());
            stmt.setString(3, member.getContactInfo());
            stmt.setString(4, member.getMembershipType().name());
            stmt.setDate(5, Date.valueOf(member.getJoinDate()));

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean removeMember(UUID memberId) {
        String sql = "DELETE FROM members WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, memberId.toString());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateMembershipType(UUID bookId, MembershipType newMembershipType) {
        String sql = "UPDATE members SET membership_type " + "= ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, String.valueOf(newMembershipType));
            stmt.setString(2, String.valueOf(bookId));

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
