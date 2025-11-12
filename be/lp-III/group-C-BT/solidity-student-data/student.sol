// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

/*
Experiment 4
Aim: Write a program in Solidity to create Student data using
Structures, Arrays, and Fallback. Deploy on Ethereum test network
and observe Gas and Transaction fees.
*/

contract StudentData {
    // Structure to store student details
    struct Student {
        uint256 rollNo;
        string name;
        uint256 marks;
    }

    // Dynamic array to store multiple students
    Student[] public students;

    // Event for logging
    event StudentAdded(uint256 rollNo, string name, uint256 marks);
    event Received(address sender, uint256 amount);

    // Function to add new student
    function addStudent(uint256 _rollNo, string memory _name, uint256 _marks) public {
        students.push(Student(_rollNo, _name, _marks));
        emit StudentAdded(_rollNo, _name, _marks);
    }

    // Function to get total number of students
    function getStudentCount() public view returns (uint256) {
        return students.length;
    }

    // Function to get details of a student by index
    function getStudent(uint256 index) public view returns (uint256, string memory, uint256) {
        require(index < students.length, "Invalid index");
        Student memory s = students[index];
        return (s.rollNo, s.name, s.marks);
    }

    // Fallback function to handle plain Ether transfers
    fallback() external payable {
        emit Received(msg.sender, msg.value);
    }

    // Receive function (optional, when Ether is sent directly)
    receive() external payable {
        emit Received(msg.sender, msg.value);
    }
}