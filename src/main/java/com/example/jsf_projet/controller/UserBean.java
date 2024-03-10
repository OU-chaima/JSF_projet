package com.example.jsf_projet.controller;

import com.example.jsf_projet.model.User;
import com.example.jsf_projet.service.UserService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.faces.context.FacesContext;

import java.util.ArrayList;
import java.util.List;
@ManagedBean(name="userBean")
@SessionScoped

public class UserBean {
    private List<User> userList = new ArrayList<User>();
    private UserService userService = new UserService();
    private int rowsPerPage =6;
    private int currentPage = 1;
    private int totalRows = 0;
    private boolean previousButtonDisabled;
    private boolean nextButtonDisabled;
    private String message;
    private String message1;
    private boolean showAddUserForm;
    private User newUser;


    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public boolean isShowAddUserForm() {
        return showAddUserForm;
    }

    public void setShowAddUserForm(boolean showAddUserForm) {
        this.showAddUserForm = showAddUserForm;
    }

    // Method to toggle the visibility of the Add User form
    public void toggleAddUserForm() {
        showAddUserForm = !showAddUserForm;
    }

    public UserBean() {
        newUser = new User();
        init();

    }

    public void toggleEditUser(User user) {
        user.setEditing(!user.isEditing());
    }

    public boolean isUserEditing(User user) {
        return user.isEditing();
    }

    public String getMessage1() {
        return message1;
    }

    public void setMessage1(String message1) {
        this.message1 = message1;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public boolean isPreviousButtonDisabled() {
        return previousButtonDisabled;
    }

    public void setPreviousButtonDisabled(boolean previousButtonDisabled) {
        this.previousButtonDisabled = previousButtonDisabled;
    }

    public boolean isNextButtonDisabled() {
        return nextButtonDisabled;
    }

    public void setNextButtonDisabled(boolean nextButtonDisabled) {
        this.nextButtonDisabled = nextButtonDisabled;
    }

    // Méthodes pour la pagination
    public void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            updateButtonDisabledState();
        }
    }

    public void nextPage() {
        int lastPage = (int) Math.ceil((double) totalRows / rowsPerPage);
        if (currentPage < lastPage) {
            currentPage++;
            updateButtonDisabledState();
        }
    }

    private void updateButtonDisabledState() {
        previousButtonDisabled = currentPage <= 1;

        int lastPage = (int) Math.ceil((double) totalRows / rowsPerPage);
        nextButtonDisabled = currentPage >= lastPage;
    }

    public void init() {
        userList.clear(); // Nettoie la liste actuelle pour éviter les doublons lors des mises à jour
        userList.addAll(userService.selectUser()); // Ajoute les données les plus récentes depuis la base de données
        updateButtonDisabledState();
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void saveChanges() {
        try {
            for (User user : userList) {
                if (user.isEditing()) {
                    userService.editUser(user);
                    init();
                    user.setEditing(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred while saving changes.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void deleteuser(String email) {
        message = userService.deleteUser(email);
        init();
    }

    public void adduser(User user) {
        message1 = userService.addUser(user);
        init();

    }
}
