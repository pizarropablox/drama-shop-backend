package com.drama.shop.drama_shop_backend.Service;

import com.drama.shop.drama_shop_backend.Model.User;
import com.drama.shop.drama_shop_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Crear un nuevo usuario
    public User createUser(User user) {
        System.out.println("Datos recibidos: " + user);
        validateUser(user); // Validaciones manuales
        return userRepository.save(user);
    }

    // Buscar un usuario por ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Modificar un usuario existente
    public User updateUser(Long id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            if (user.getUsername() != null) {
                existingUser.setUsername(user.getUsername());
            }
            if (user.getPassword() != null) {
                existingUser.setPassword(user.getPassword());
            }
            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getRole() != null) {
                existingUser.setRole(user.getRole());
            }
            return userRepository.save(existingUser);
        }
        return null;
    }

    // Eliminar un usuario por ID
    public boolean deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Método de validación manual
    private void validateUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario es obligatorio");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El correo electrónico es obligatorio");
        }
        if (user.getRole() == null || user.getRole().isEmpty()) {
            throw new IllegalArgumentException("El rol es obligatorio");
        }
    }
}
