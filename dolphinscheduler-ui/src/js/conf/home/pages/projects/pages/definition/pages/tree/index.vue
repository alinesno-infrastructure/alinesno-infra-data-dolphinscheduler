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
  <m-list-construction :title="$t('TreeView')">
    <template slot="operation">
      <span style=" float: right; padding-right:50px">
        <em class="el-icon-back" style="font-size:20px " data-container="body" data-toggle="tooltip" :title="$t('Return')" @click="_close()"></em>
      </span>
    </template>
    <template slot="conditions"></template>
    <template slot="content">
      <div class="tree-view-index-model">
        <div class="tree-limit-select">
          <el-select v-model="limit" style="width: 70px;" @change="_onChangeSelect" size="small">
            <el-option
                    v-for="city in [{value:25},{value:50},{value:75},{value:100}]"
                    :key="city.value"
                    :value="city.value"
                    :label="city.value">
            </el-option>
          </el-select>
          <el-button
                  @click="_rtTasksDag"
                  v-if="$route.query.subProcessCodes"
                  type="primary"
                  size="small"
                  icon="el-icon-d-arrow-left">
            {{$t('Return_1')}}
          </el-button>
        </div>
        <div class="tasks-color">
          <div class="toolbar-color-sp">
            <a href="javascript:">
              <span>Node Type</span>
            </a>
            <a href="javascript:" v-for="(k,v) in tasksType" :key="v">
              <em class="el-icon-user-solid" :style="{color:k.color}"></em>
              <span>{{v}}</span>
            </a>
          </div>
          <div class="state-tasks-color-sp">
            <a href="javascript:">
              <span>{{$t('Task Status')}}</span>
            </a>
            <a href="javascript:" v-for="(item) in tasksState" :key="item.id">
              <em class="ri-checkbox-blank-fill" :style="{color:item.color}"></em>
              <span>{{item.desc}}</span>
            </a>
          </div>
        </div>
        <div class="tree-model" v-show="!isNodata">
          <div class="d3-tree">
            <svg class='tree' width="100%"></svg>
          </div>
        </div>
        <m-no-data v-if="isNodata"></m-no-data>
      </div>
      <m-spin :is-spin="isLoading"></m-spin>
    </template>
  </m-list-construction>

</template>
<script>
  import _ from 'lodash'
  import { mapActions, mapState } from 'vuex'
  import Tree from './_source/tree'
  import { uuid } from '@/module/util'
  import mSpin from '@/module/components/spin/spin'
  import mNoData from '@/module/components/noData/noData'
  import { tasksType, tasksState } from '@/conf/home/pages/dag/_source/config'
  import mListConstruction from '@/module/components/listConstruction/listConstruction'

  export default {
    name: 'tree-view-index-index',
    data () {
      return {
        // limit
        limit: 25,
        // loading
        isLoading: true,
        // node type
        tasksType: tasksType,
        // node state
        tasksState: tasksState,
        // tree data
        treeData: {},
        // is data
        isNodata: false
      }
    },
    props: {},
    methods: {
      ...mapActions('dag', ['getViewTree']),
      _close () {
        this.$router.go(-1)
      },
      /**
       * get tree data
       */
      _getViewTree () {
        this.isLoading = true

        Tree.reset()

        this.getViewTree({
          code: this.$route.params.code,
          limit: this.limit
        }).then(res => {
          let data = _.cloneDeep(res)
          this.treeData = data
          if (!this.treeData.children) {
            this.isLoading = false
            this.isNodata = true
            return
          }
          let recursiveChildren = (children) => {
            if (children.length) {
              _.map(children, v => {
                v.uuid = `${uuid('uuid_')}${uuid() + uuid()}`
                if (v.children.length) {
                  recursiveChildren(v.children)
                }
              })
            }
          }
          recursiveChildren(data.children)
          // init tree
          Tree.init({
            data: _.cloneDeep(data),
            limit: this.limit,
            selfTree: this
          }).then(() => {
            setTimeout(() => {
              // this.isLoading = false
            }, 100)
          })
        }).catch(e => {
          this.isLoading = false
          if (!e.data) {
            this.isNodata = true
          }
        })
      },

      /**
       * Return to the previous child node
       */
      _rtTasksDag () {
        let subProcessCodes = this.$route.query.subProcessCodes
        let codeList = subProcessCodes.split(',')
        let codes = codeList.slice(0, codeList.length - 1)
        let code = codeList[codeList.length - 1]
        let query = {}

        if (code !== codeList[0]) {
          query = { subProcessCodes: codes.join(',') }
        }
        this.$router.push({ path: `/projects/${this.projectCode}/definition/tree/${code}`, query: query })
      },
      /**
       * Subprocess processing
       * @param subProcessCode 子流程Code
       */
      _subProcessHandle (subProcessCode) {
        let subProcessCodes = []
        let codes = this.$route.query.subProcessCodes
        if (codes) {
          let newCode = codes.split(',')
          newCode.push(this.$route.params.code)
          subProcessCodes = newCode
        } else {
          subProcessCodes.push(this.$route.params.code)
        }
        this.$router.push({ path: `/projects/${this.projectCode}/definition/tree/${subProcessCode}`, query: { subProcessCodes: subProcessCodes.join(',') } })
      },
      _onChangeSelect (o) {
        this.limit = o
        this._getViewTree()
      }
    },
    watch: {
      '$route.params.code' () {
        this._getViewTree()
      }
    },
    created () {
      this._getViewTree()
    },
    mounted () {
    },
    computed: {
      ...mapState('dag', ['projectCode'])
    },
    components: { mSpin, mListConstruction, mNoData }
  }
</script>

<style lang="scss" rel="stylesheet/scss">

  .tree-view-index-model {
    background: url('img/dag_bg.png');
    position: relative;
    .tree-limit-select {
      position: absolute;
      right: 20px;
      top: 22px;
      z-index: 1;
    }
    .tasks-color {
      min-height: 76px;
      background: #fff;
      padding-left: 20px;
      position: relative;
      padding-bottom: 10px;
      .toolbar-color-sp {
        padding: 12px 0;
      }

    }
    .tree-model {
      width: calc(100%);
      height: calc(100vh - 224px);
      overflow-x: scroll;
    }
    .d3-tree {
      padding-left: 30px;
      .node {
        text {
          font: 11px sans-serif;
          pointer-events: none;
        }
      }
      rect {
        cursor: pointer;
        &.state {
          stroke: #666;
          shape-rendering: crispEdges;
        }
      }
      path {
        &.link{
          fill: none;
          stroke: #666;
          stroke-width: 2px;
        }
      }
    }
  }

</style>
