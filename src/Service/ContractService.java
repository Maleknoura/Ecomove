package Service;

import DAO.ContractDAO;
import Model.Contract;

import java.util.List;
import java.util.UUID;

public class ContractService {
    private ContractDAO contractDAO;

    public ContractService(ContractDAO contractDAO) {
        this.contractDAO = contractDAO;
    }

    public boolean createContract(Contract contract) {

        contractDAO.createContract(contract);
        return true;
    }
    public Contract getContractById(UUID id) {
        return contractDAO.getContractById(id);
    }

    public boolean updateContract(Contract contract) {
        contractDAO.updateContract(contract);
        return true;
    }

    public void deleteContract(UUID id) {
        contractDAO.deleteContract(id);
    }

    public List<Contract> getAllContracts() {
        return contractDAO.getAllContracts();
    }
}

