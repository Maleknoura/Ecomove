package Service;

import DAO.ContractDAO;
import Model.Contract;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class ContractService {
    private ContractDAO contractDAO;

    public ContractService(ContractDAO contractDAO) {
        this.contractDAO = contractDAO;
    }

    public void createContract(Contract contract) {
        contractDAO.createContract(contract);
    }

    public Contract getContractById(UUID id) {
        return contractDAO.getContractById(id);
    }

    public void updateContract(Contract contract) {
        contractDAO.updateContract(contract);
    }

    public void deleteContract(UUID id) {
        contractDAO.deleteContract(id);
    }

    public List<Contract> getAllContracts() {
        return contractDAO.getAllContracts();
    }
}

