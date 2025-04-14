import React, { useState } from 'react';
import { View, Text, TextInput, Button, Alert, StyleSheet } from 'react-native';
import api from '../services/api'; // Caminho relativo corrigido

const RegisterScreen = () => {
  const [form, setForm] = useState({
    name: '',
    cpf: '',
    email: '',
    password: '',
    role: 'USER'
  });

  const handleChange = (field: string, value: string) => {
    setForm(prev => ({...prev, [field]: value}));
  };

  const handleRegister = async () => {
    try {
      // Validação simples dos campos
      if (!form.name || !form.cpf || !form.email || !form.password) {
        Alert.alert("Atenção", "Preencha todos os campos!");
        return;
      }

      const response = await api.post('/v1/users/userRegistration', form);

      if (response.status === 201) {
        Alert.alert("Sucesso!", "Usuário cadastrado com sucesso.");
        // Limpa o formulário após cadastro
        setForm({
          name: '',
          cpf: '',
          email: '',
          password: '',
          role: 'USER'
        });
      }
    } catch (error: any) {
      Alert.alert(
        "Erro", 
        error.response?.data?.message || "Falha no cadastro"
      );
    }
  };

  return (
    <View style={styles.container}>
      <Text>Nome:</Text>
      <TextInput
        style={styles.input}
        value={form.name}
        onChangeText={(text) => handleChange('name', text)}
        placeholder="Digite seu nome"
      />

      <Text>CPF:</Text>
      <TextInput
        style={styles.input}
        value={form.cpf}
        onChangeText={(text) => handleChange('cpf', text)}
        placeholder="Digite seu CPF"
        keyboardType="numeric"
      />

      <Text>E-mail:</Text>
      <TextInput
        style={styles.input}
        value={form.email}
        onChangeText={(text) => handleChange('email', text)}
        placeholder="Digite seu e-mail"
        keyboardType="email-address"
        autoCapitalize="none"
      />

      <Text>Senha:</Text>
      <TextInput
        style={styles.input}
        value={form.password}
        onChangeText={(text) => handleChange('password', text)}
        placeholder="Digite sua senha"
        secureTextEntry
      />

      <Button title="Cadastrar" onPress={handleRegister} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    padding: 20,
  },
  input: {
    height: 40,
    borderColor: 'gray',
    borderWidth: 1,
    marginBottom: 12,
    paddingHorizontal: 8,
  },
});

export default RegisterScreen;