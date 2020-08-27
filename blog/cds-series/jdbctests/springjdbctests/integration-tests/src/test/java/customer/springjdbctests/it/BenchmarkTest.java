package customer.springjdbctests.it;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import customer.springjdbctests.Application;

@SpringBootTest(classes = Application.class)
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@AutoConfigureMockMvc
public class BenchmarkTest {
    
    private static MockMvc mockMvc;

    @Autowired
    public void setMockMvc(MockMvc mockMvc){
        BenchmarkTest.mockMvc = mockMvc;
    }

    @Test
    public void executeJmhRunner() throws RunnerException {
        Options jmhOptions = new OptionsBuilder()
        .include("\\."+this.getClass().getSimpleName()+"\\.")
        .warmupIterations(3)
        .measurementIterations(50)
        .forks(0)
        .threads(1)
        .shouldDoGC(true)
        .shouldFailOnError(true)
        .resultFormat(ResultFormatType.TEXT)
        .result("."+File.separator+"result.log")
        .jvmArgs("-server")
        .build();

        new Runner(jmhOptions).run();
    }  

    @Benchmark
    public void catalogBenchmark() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/odata/v4/CatalogService/Books")).andExpect(status().isOk())
				.andExpect(jsonPath("$.value[0].title").value(containsString("Wuthering Heights")))
				.andExpect(jsonPath("$.value[0].stock").value(100))
				.andExpect(jsonPath("$.value[1].title").value(containsString("Jane Eyre (discounted)")))
				.andExpect(jsonPath("$.value[1].stock").value(500));
    }

    @Benchmark
    public void adminBenchmark() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/odata/v4/AdminService/Books")).andExpect(status().isOk())
				.andExpect(jsonPath("$.value[0].title").value(containsString("Wuthering Heights")))
				.andExpect(jsonPath("$.value[0].stock").value(100))
				.andExpect(jsonPath("$.value[1].title").value(containsString("Jane Eyre (discounted)")))
				.andExpect(jsonPath("$.value[1].stock").value(500));
    }
}