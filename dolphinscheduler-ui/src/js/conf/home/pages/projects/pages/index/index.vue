/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
<template>
  <m-list-construction :title="searchParams.projectCode ? `${$t('Project Home')} - ${projectName}` : $t('Home')">
  <template slot="content">
    <div class="perject-home-content">
      <div class="time-model">
        <el-date-picker v-model="dataTime" type="datetimerange" size="small" @change="_datepicker" range-separator="-"
          :start-placeholder="$t('startDate')" :end-placeholder="$t('endDate')" value-format="yyyy-MM-dd HH:mm:ss">
        </el-date-picker>
      </div>

      <!-- <el-container>
          <el-container>
            <el-main>
              <div class="row">
                <div class="col-md-12">
                  <div class="chart-title" style="margin-bottom: -20px;margin-top: 30px">
                      <span>{{ $t('Process Definition Statistics') }}</span>
                    </div>
                    <div>
                      <m-define-user-count :project-code="searchParams.projectCode">
                      </m-define-user-count>
                    </div>
                  </div>
                </div>

              </el-main>
              <el-aside width="30%">

              
              </el-aside>
            </el-container>
          </el-container> -->

        <div class="row">
          <!-- <div class="col-md-4">
            <div class="chart-title" style="margin-bottom: -20px;margin-top: 30px">
              <span>{{ $t('Process Definition Statistics') }}</span>
            </div>
            <div>
              <m-define-user-count :project-code="searchParams.projectCode">
              </m-define-user-count>
            </div>
          </div> -->
          <div class="col-md-6">
            <div class="chart-title">
              <span>{{ $t('Task status statistics') }}</span>
            </div>
            <div class="row">
              <m-task-status-count :search-params="searchParams">
              </m-task-status-count>
            </div>
          </div>
          <div class="col-md-6">
            <div class="chart-title">
              <span>{{ $t('Process Status Statistics') }}</span>
            </div>
            <div class="row">
              <m-process-state-count :search-params="searchParams">
              </m-process-state-count>
            </div>
          </div>
        </div>

      </div>
    </template>
  </m-list-construction>
</template>
<script>
import { mapState } from 'vuex'
import dayjs from 'dayjs'
import mDefineUserCount from './_source/defineUserCount'
import mTaskStatusCount from './_source/taskStatusCount'
import mProcessStateCount from './_source/processStateCount'
import mListConstruction from '@/module/components/listConstruction/listConstruction'

export default {
  name: 'projects-index-index',
  data() {
    return {
      searchParams: {
        projectCode: null,
        startDate: '',
        endDate: ''
      },
      dataTime: []
    }
  },
  methods: {
    _datepicker(val) {
      this.searchParams.startDate = val[0]
      this.searchParams.endDate = val[1]
    }
  },
  created() {
    this.searchParams.projectCode = this.$route.params.projectCode == null ? 0 : this.$route.params.projectCode
    this.dataTime[0] = dayjs().format('YYYY-MM-DD 00:00:00')
    this.dataTime[1] = dayjs().format('YYYY-MM-DD HH:mm:ss')
    this.searchParams.startDate = this.dataTime[0]
    this.searchParams.endDate = this.dataTime[1]
  },
  components: {
    mListConstruction,
    mDefineUserCount,
    mTaskStatusCount,
    mProcessStateCount
  },
  computed: {
    ...mapState('dag', ['projectName'])
  }
}
</script>

<style lang="scss" rel="stylesheet/scss">
.perject-home-content {
  padding: 10px 20px;
  position: relative;

  .time-model {
    position: absolute;
    right: 8px;
    top: -40px;

    .ans-input {
      >input {
        width: 344px;
      }
    }
  }

  .chart-title {
    text-align: center;
    height: 60px;
    line-height: 60px;

    span {
      font-size: 22px;
      color: #333;
      font-weight: bold;
    }
  }
}

.table-small-model {
  .ellipsis {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    display: block;
  }
}
</style>
