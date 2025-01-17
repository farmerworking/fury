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
import org.openjdk.jmh.infra.Blackhole;

public class LongsDeserializationSuite {

  @Benchmark
  public Object kryo_deserializeLongs(KryoState.ReadLongsState state) {
    state.input.setPosition(0);
    return state.kryo.readClassAndObject(state.input);
  }

  @Benchmark
  public Object fury_deserializeLongs(FuryState.ReadLongsState state) {
    state.buffer.readerIndex(0);
    return state.fury.deserialize(state.buffer);
  }

  @Benchmark
  public Object fst_deserializeLongs(FstState.ReadLongsState state, Blackhole bh) {
    return FstState.FstBenchmarkState.deserialize(bh, state);
  }

  @Benchmark
  public Object hession_deserializeLongs(HessionState.ReadLongsState state) {
    state.bis.reset();
    state.input.reset();
    return HessionState.deserialize(state.input);
  }

  // @Benchmark
  public Object protostuff_deserializeLongs(ProtostuffState.ReadLongsState state) {
    return ProtostuffState.deserialize(state.schema, state.protoStuff);
  }

  @Benchmark
  public Object jdk_deserializeLongs(JDKState.ReadLongsState state) {
    state.bis.reset();
    return JDKState.deserialize(state.bis);
  }
}
