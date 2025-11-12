// SPDX-License-Identifier: MIT
pragma solidity ^0.8.19;

/// @title Simple E-Voting Smart Contract
/// @author
/// @notice Owner can add candidates and control the election lifecycle.
/// @dev Each voter can vote only once per election.
contract EVoting {
    address public owner;
    bool public electionActive;
    uint256 public candidateCount;

    struct Candidate {
        uint256 id;
        string name;
        uint256 voteCount;
    }

    struct Voter {
        bool voted;
        uint256 votedFor; // candidate id
    }

    mapping(uint256 => Candidate) private candidates;
    mapping(address => Voter) private voters;

    event CandidateAdded(uint256 indexed id, string name);
    event ElectionStarted();
    event ElectionEnded();
    event Voted(address indexed voter, uint256 indexed candidateId);
    event OwnershipTransferred(address indexed oldOwner, address indexed newOwner);

    modifier onlyOwner() {
        require(msg.sender == owner, "Only owner can perform this action");
        _;
    }

    modifier whenElectionActive() {
        require(electionActive, "Election is not active");
        _;
    }

    modifier whenElectionNotActive() {
        require(!electionActive, "Election is already active");
        _;
    }

    constructor() {
        owner = msg.sender;
    }

    /// @notice Add a candidate. Only owner before election starts.
    function addCandidate(string calldata _name) external onlyOwner whenElectionNotActive {
        require(bytes(_name).length > 0, "Candidate name required");
        candidateCount++;
        candidates[candidateCount] = Candidate(candidateCount, _name, 0);
        emit CandidateAdded(candidateCount, _name);
    }

    /// @notice Start the election. Only owner.
    function startElection() external onlyOwner whenElectionNotActive {
        require(candidateCount > 0, "No candidates added");
        electionActive = true;
        emit ElectionStarted();
    }

    /// @notice End the election. Only owner.
    function endElection() external onlyOwner whenElectionActive {
        electionActive = false;
        emit ElectionEnded();
    }

    /// @notice Cast a vote for a candidate by id. Each address can vote only once.
    function vote(uint256 _candidateId) external whenElectionActive {
        require(_candidateId > 0 && _candidateId <= candidateCount, "Invalid candidate ID");
        Voter storage sender = voters[msg.sender];
        require(!sender.voted, "You have already voted");

        sender.voted = true;
        sender.votedFor = _candidateId;
        candidates[_candidateId].voteCount++;

        emit Voted(msg.sender, _candidateId);
    }

    /// @notice Get candidate details by id.
    function getCandidate(uint256 _candidateId)
        external
        view
        returns (uint256 id, string memory name, uint256 voteCount)
    {
        require(_candidateId > 0 && _candidateId <= candidateCount, "Invalid candidate ID");
        Candidate storage c = candidates[_candidateId];
        return (c.id, c.name, c.voteCount);
    }

    /// @notice Get total number of candidates.
    function getCandidatesCount() external view returns (uint256) {
        return candidateCount;
    }

    /// @notice Check if a voter has voted and for whom.
    function hasVoted(address _voter) external view returns (bool voted, uint256 votedFor) {
        Voter storage v = voters[_voter];
        return (v.voted, v.votedFor);
    }

    /// @notice Transfer ownership to a new address. Only owner.
    function transferOwnership(address _newOwner) external onlyOwner {
        require(_newOwner != address(0), "Invalid address");
        address oldOwner = owner;
        owner = _newOwner;
        emit OwnershipTransferred(oldOwner, _newOwner);
    }
}
