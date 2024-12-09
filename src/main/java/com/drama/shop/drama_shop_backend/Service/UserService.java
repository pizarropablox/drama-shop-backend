package com.drama.shop.drama_shop_backend.Service;

import com.drama.shop.drama_shop_backend.Model.User;
import com.drama.shop.drama_shop_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyección del PasswordEncoder

    // Obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Crear un nuevo usuario
    public User createUser(User user) {
        validateUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encriptar contraseña
        return userRepository.save(user);
    }

    // Buscar un usuario por ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Modificar un usuario existente
    public User updateUser(Long id, User user) {
        validateUser(user);
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setUsername(user.getUsername());
            if (!user.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(user.getPassword())); // Encriptar contraseña
            }
            existingUser.setEmail(user.getEmail());
            existingUser.setRole(user.getRole());
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

    public boolean authenticateUser(String email, String rawPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email); // Manejar Optional
        if (userOptional.isPresent()) {
            User user = userOptional.get(); // Extraer el usuario
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        return false; // Usuario no encontrado o contraseña inválida
    }
    
    

}
