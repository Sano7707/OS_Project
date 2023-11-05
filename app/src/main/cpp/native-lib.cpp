#include <iostream>
#include <vector>
#include <thread>
#include <cmath>
#include <jni.h>

double calculatePartialComputation(int n, int step) {
    double partialResult = 0.0;

    for (int i = 0; i <= n; i += step) {
        partialResult += exp(sin(i) * cos(i));
    }

    return partialResult;
}

extern "C"
JNIEXPORT jdouble JNICALL
Java_com_example_os_1project_FactorialCalculator_performComplexComputationWithThreadsC(JNIEnv *env,
                                                                                      jobject thiz,
                                                                                      jint n,
                                                                                      jint numThreads) {
    if (n < 0) {
        throw std::invalid_argument("Complex computation is not defined for negative numbers");
    }

    std::vector<std::thread> threads(numThreads);
    std::vector<double> partialResults(numThreads);

    for (int i = 0; i < numThreads; i++) {
        threads[i] = std::thread([n, i, numThreads, &partialResults]() {
            partialResults[i] = calculatePartialComputation(n, 1);
        });
    }

    for (int i = 0; i < numThreads; i++) {
        threads[i].join();
    }

    double result = 0.0;
    for (double partialResult : partialResults) {
        result += partialResult;
    }

    return result;

}