package com.clinica.model;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Tacyrose
 */
import org.jbehave.core.annotations.*;
import org.jbehave.core.steps.Steps;
import com.clinica.model.Paciente;

public class MySteps  extends Steps{
	Paciente paci;
	@Given("que eu acesse o cadastrar usuário")
	public void CadastroUsuarioTest(){
		 paci = new Paciente();
	}
	@When("o rg $rg")
	public void InserirRGTest(@Named("rg") String rg){
                 paci.setRg(rg);
	}
	@When("eu entrar com nome $nome")
	public void InserirNomeTest(@Named("nome") String nome){
		 paci.setNome(nome);
	}
        @When("o sexo $sexo")
	public void InserirSexoTest(@Named("sexo") String sexo){
		 paci.setSexo(sexo);
        }
        @When("o telefone $telefone")
	public void InserirTelefoneTest(@Named("telefone") Telefone telefone){
		 paci.setTelefone(telefone);
        }    
	@When("o cpf $cpf")
	public void InserirCPFTest(@Named("cpf") long cpf){
		 paci.setCpf(cpf);
        }
        @When("o endereco $endereco")
	public void InserirEnderecoTest(@Named("endereco") Endereco endereco){
		 paci.setEndereco(endereco);
        }
         @When("a data_nasc $data_nasc")
	public void InserirDataNascimentoTest(@Named("data_nasc") String data_nasc){
		 paci.setData_nasc(data_nasc);
        }
         @When("pSaude $pSaude")
	public void InserirPlanoTest(@Named("pSaude") String pSaude){
		 paci.setpSaude(pSaude);
        }
        @When("email $email")
	public void InserirEmailTest(@Named("pSaude") String email){
		 paci.setEmail(email);
        }
       
	@Then("o sistema responde Paciente $nome criado com sucesso")
	public void ValidarCadastroTest(@Named("nome") String nome){
        assertEquals(nome, paci.cadastrarPaciente());
        System.out.println(nome);
        System.out.println(paci.cadastrarPaciente());
	}
}

