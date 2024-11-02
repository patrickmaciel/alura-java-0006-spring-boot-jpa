package br.com.alura.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConsultaChatGPT {
  public static String obterTraducao(String texto) {
    Properties configProperties = new Properties();
    String openAiApiKey;

    try {
      configProperties.load(new FileInputStream("config.properties"));
      openAiApiKey = configProperties.getProperty("openAiApiKey");
//      configProperties.load(ConsultaChatGPT.class.getResourceAsStream("/config.properties"));
    } catch (Exception e) {
      throw new RuntimeException("Erro ao carregar arquivo de configuração", e);
    }

    OpenAiService service = new OpenAiService(openAiApiKey);

    CompletionRequest requisicao = CompletionRequest.builder()
        .model("gpt-3.5-turbo-instruct")
        .prompt("traduza para o português o texto: " + texto)
        .maxTokens(1000)
        .temperature(0.7)
        .build();

    var resposta = service.createCompletion(requisicao);
    return resposta.getChoices().get(0).getText();
  }
}