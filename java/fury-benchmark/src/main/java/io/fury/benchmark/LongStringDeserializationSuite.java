/*
 * Copyright 2023 The Fury Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.fury.benchmark;

import io.fury.benchmark.state.FstState;
import io.fury.benchmark.state.FuryState;
import io.fury.benchmark.state.HessionState;
import io.fury.benchmark.state.JDKState;
import io.fury.benchmark.state.KryoState;
import io.fury.benchmark.state.ProtostuffState;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMode(Mode.Throughput)
@CompilerControl(value = CompilerControl.Mode.INLINE)
public class LongStringDeserializationSuite {
  @Benchmark
  public Object kryo_deserializeLongStr(KryoState.ReadLongStrState state) {
    state.input.setPosition(0);
    return state.kryo.readClassAndObject(state.input);
  }

  @Benchmark
  public Object fury_deserializeLongStr(FuryState.ReadLongStrState state) {
    state.buffer.readerIndex(0);
    return state.fury.deserialize(state.buffer);
  }

  @Benchmark
  public Object fst_deserializeLongStr(FstState.ReadLongStrState state, Blackhole bh) {
    return FstState.FstBenchmarkState.deserialize(bh, state);
  }

  @Benchmark
  public Object hession_deserializeLongStr(HessionState.ReadLongStrState state) {
    state.bis.reset();
    state.input.reset();
    return HessionState.deserialize(state.input);
  }

  @Benchmark
  public Object protostuff_deserializeLongStr(ProtostuffState.ReadLongStrState state) {
    return ProtostuffState.deserialize(state.schema, state.protoStuff);
  }

  @Benchmark
  public Object jdk_deserializeLongStr(JDKState.ReadLongStrState state) {
    state.bis.reset();
    return JDKState.deserialize(state.bis);
  }
}
