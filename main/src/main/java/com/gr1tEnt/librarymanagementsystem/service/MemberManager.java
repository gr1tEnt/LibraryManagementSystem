package com.gr1tEnt.librarymanagementsystem.service;

import com.gr1tEnt.librarymanagementsystem.model.Member;
import com.gr1tEnt.librarymanagementsystem.model.MembershipType;
import com.gr1tEnt.librarymanagementsystem.utils.InputHelper;
import com.gr1tEnt.librarymanagementsystem.utils.InputValidator;

import java.util.Scanner;
import java.util.UUID;

public class MemberManager implements MemberManagerInterface {
    private final Scanner scanner = new Scanner(System.in);
    private final MemberService memberService;
    private final InputHelper inputHelper;

    public MemberManager(MemberService memberService, InputHelper inputHelper) {
        this.memberService = memberService;
        this.inputHelper = inputHelper;
    }

    @Override
    public void addNewMember() {
        String name = inputHelper.getValidStringInput("Enter your name: ");
        String email = inputHelper.getValidStringInput("Enter your email: ");

        Member member = new Member(name, email);

        if (memberService.addMember(member)) {
            System.out.println("Member added successfully");
        } else {
            System.out.println("Failed to add the member. Please check the input values.");
        }
    }

    @Override
    public void removeMember() {
        UUID memberId = inputHelper.getValidIdInput();

        if (memberService.removeMember(memberId)) {
            System.out.println("Member removed successfully.");
        } else {
            System.out.println("No book found with such ID.");
        }
    }

    @Override
    public void updateMembershipType() {
        UUID memberId = inputHelper.getValidIdInput();
        MembershipType membershipType = inputHelper.getValidMembershipType();

        if (memberService.updateMembershipType(memberId, membershipType)) {
            System.out.println("Membership type updated successfully.");
        } else {
            System.out.println("No member found with such ID.");
        }
    }
}
