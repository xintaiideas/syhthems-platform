<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-layout
            row
            wrap
    >
        <v-flex sm12>
            <v-card>
                <v-toolbar class="mb-2">
                    <v-btn
                            flat
                            :to="toProduct"
                    >
                        <v-icon>$vuetify.icons.prev</v-icon>
                        <span>返回首页页面</span>
                    </v-btn>
                    <v-spacer />
                    <v-toolbar-title>用户管理</v-toolbar-title>
                    <v-spacer />
                    <v-btn
                            icon
                            @click="getDevices()"
                    >
                        <v-icon>$vuetify.icons.refresh</v-icon>
                    </v-btn>
                </v-toolbar>
                <v-container
                        fluid
                        grid-list-md
                >
                    <v-data-iterator
                            :items="devices"
                            content-tag="v-layout"
                            hide-actions
                            no-data-text="该产品没有设备"
                            row
                            wrap
                            justify-space-between
                    >
                        <template v-slot:header="">
                            <v-toolbar
                                    class="mb-2"
                            >
                                <v-toolbar-title>用户列表</v-toolbar-title>
                                <v-spacer />
                                <v-dialog
                                        v-model="newDeviceDialog"
                                        persistent
                                        max-width="400px"
                                >
                                    <template v-slot:activator="{ on }">
                                        <v-btn
                                                icon
                                                fab
                                                dark
                                                color="pink"
                                                v-on="on"
                                        >
                                            <v-icon>$vuetify.icons.add</v-icon>
                                        </v-btn>
                                    </template>
                                    <v-card>
                                        <v-card-title>
                                            <span class="headline">添加用户</span>
                                        </v-card-title>
                                        <v-card-text>
                                            <v-form
                                                    ref="newDeviceForm"
                                                    v-model="newDeviceFormScheme.valid"
                                                    lazy-validation
                                            >
                                                <v-container>
                                                    <v-layout wrap>
                                                        <v-flex
                                                                xs12
                                                        >
                                                            <v-text-field
                                                                    v-model="newDeviceFormScheme.model.userId"
                                                                    :label="newDeviceFormScheme.fields.userId.label"
                                                                    :rules="newDeviceFormScheme.fields.userId.rules"
                                                                    :required="newDeviceFormScheme.fields.userId.required"
                                                            />
                                                        </v-flex>
                                                        <v-flex
                                                                xs12
                                                        >
                                                            <v-text-field
                                                                    v-model="newDeviceFormScheme.model.username"
                                                                    :label="newDeviceFormScheme.fields.username.label"
                                                                    :rules="newDeviceFormScheme.fields.username.rules"
                                                                    :required="newDeviceFormScheme.fields.username.required"
                                                            />
                                                        </v-flex>

                                                        <v-flex
                                                                xs12
                                                        >
                                                            <v-text-field
                                                                    v-model="newDeviceFormScheme.model.password"
                                                                    :label="newDeviceFormScheme.fields.password.label"
                                                                    :rules="newDeviceFormScheme.fields.password.rules"
                                                            />
                                                        </v-flex>

                                                        <v-flex
                                                                xs12
                                                        >
                                                            <v-text-field
                                                                    v-model="newDeviceFormScheme.model.email"
                                                                    :label="newDeviceFormScheme.fields.email.label"
                                                                    :rules="newDeviceFormScheme.fields.email.rules"
                                                            />
                                                        </v-flex>
                                                        <v-flex
                                                                xs12
                                                        >
                                                            <v-text-field
                                                                    v-model="newDeviceFormScheme.model.phone"
                                                                    :label="newDeviceFormScheme.fields.phone.label"
                                                                    :rules="newDeviceFormScheme.fields.phone.rules"
                                                            />
                                                        </v-flex>
                                                        <v-flex
                                                                xs12
                                                        >
                                                            <v-text-field
                                                                    v-model="newDeviceFormScheme.model.header"
                                                                    :label="newDeviceFormScheme.fields.header.label"
                                                                    :rules="newDeviceFormScheme.fields.header.rules"
                                                            />
                                                        </v-flex>
                                                        <v-flex
                                                                xs12
                                                        >
                                                            <v-text-field
                                                                    v-model="newDeviceFormScheme.model.userStatus"
                                                                    :label="newDeviceFormScheme.fields.userStatus.label"
                                                                    :rules="newDeviceFormScheme.fields.userStatus.rules"
                                                            />
                                                        </v-flex>
                                                        <v-flex
                                                                xs12
                                                        >
                                                            <v-text-field
                                                                    v-model="newDeviceFormScheme.model.userDescription"
                                                                    :label="newDeviceFormScheme.fields.userDescription.label"
                                                                    :rules="newDeviceFormScheme.fields.userDescription.rules"
                                                            />
                                                        </v-flex>

                                                    </v-layout>
                                                </v-container>
                                            </v-form>
                                        </v-card-text>
                                        <v-card-actions>
                                            <v-spacer />
                                            <v-btn
                                                    color="info"
                                                    @click="clearNewDeviceForm()"
                                            >
                                                返回
                                            </v-btn>
                                            <v-btn
                                                    color="success"
                                                    @click="addDevice()"
                                            >
                                                保存
                                            </v-btn>
                                        </v-card-actions>
                                    </v-card>
                                </v-dialog>
                            </v-toolbar>
                        </template>
                        <template v-slot:item="props">
                            <v-flex
                                    xs12
                                    sm6
                                    md5
                            >

                                    <v-list dense>
                                        <v-list-tile>
                                            <v-list-tile-content class="title">
                                                用户ID：
                                            </v-list-tile-content>
                                            <v-list-tile-content class="align-end">
                                                {{ props.item.userId }}
                                            </v-list-tile-content>
                                        </v-list-tile>
                                        <v-divider />
                                        <v-list-tile>
                                            <v-list-tile-content class="title">
                                                用户名：
                                            </v-list-tile-content>
                                            <v-list-tile-content class="align-end">
                                                {{ props.item.username }}
                                            </v-list-tile-content>
                                        </v-list-tile>
                                        <v-divider />
                                        <v-list-tile>
                                            <v-list-tile-content>密码： </v-list-tile-content>
                                            <v-list-tile-content class="align-end">
                                                {{ props.item.password }}
                                            </v-list-tile-content>
                                        </v-list-tile>
                                        <v-divider />
                                        <v-list-tile>
                                            <v-list-tile-content>邮箱地址：</v-list-tile-content>
                                            <v-list-tile-content class="align-end">
                                                {{ props.item.email }}
                                            </v-list-tile-content>
                                        </v-list-tile>

                                        <v-divider />
                                        <v-list-tile>
                                            <v-list-tile-content>电话号码：</v-list-tile-content>
                                            <v-list-tile-content class="align-end">
                                                {{ props.item.phone }}
                                            </v-list-tile-content>
                                        </v-list-tile>

                                        <v-divider />
                                        <v-list-tile>
                                            <v-list-tile-content> 用户头像：</v-list-tile-content>
                                            <v-list-tile-content class="align-end">
                                                {{ props.item.header }}
                                            </v-list-tile-content>
                                        </v-list-tile>
                                        <v-divider />
                                        <v-list-tile>
                                            <v-list-tile-content> 状态：</v-list-tile-content>
                                            <v-list-tile-content class="align-end">
                                                {{ props.item.userStatus }}
                                            </v-list-tile-content>
                                        </v-list-tile>
                                        <v-divider />
                                        <v-list-tile>
                                            <v-list-tile-content> 用户简介：</v-list-tile-content>
                                            <v-list-tile-content class="align-end">
                                                {{ props.item.userDescription }}
                                            </v-list-tile-content>
                                        </v-list-tile>
                                    </v-list>
                                    <v-divider />
                                    <v-card-actions>
                                        <v-btn
                                                dark
                                                color="warning"
                                                @click.stop="deleteDevice(props.item.userId)"
                                        >
                                            删除
                                        </v-btn>
                                        <v-spacer />
                                        <v-btn
                                                dark
                                                color="info"
                                                @click.stop="updateDeviceButton(props.item)"
                                        >
                                            修改
                                        </v-btn>
                                    </v-card-actions>
                            </v-flex>
                        </template>
                    </v-data-iterator>
                </v-container>
            </v-card>
        </v-flex>
        <v-dialog
                v-model="updateDeviceDialog"
                persistent
                max-width="400px"
        >
            <v-card>
                <v-card-title>
                    <span class="headline">修改用户</span>
                </v-card-title>
                <v-card-text>
                    <v-form
                            ref="updateDeviceForm"
                            v-model="updateDeviceFormScheme.valid"
                            lazy-validation
                    >
                        <v-container>
                            <v-layout wrap>
                                <v-flex
                                        xs12
                                >
                                    <v-text-field
                                            v-model="updateDeviceFormScheme.model.name"
                                            :label="updateDeviceFormScheme.fields.name.label"
                                            :rules="updateDeviceFormScheme.fields.name.rules"
                                            :required="updateDeviceFormScheme.fields.name.required"
                                    />
                                </v-flex>
                                <v-flex
                                        xs12
                                >
                                    <v-text-field
                                            v-model="updateDeviceFormScheme.model.code"
                                            :label="updateDeviceFormScheme.fields.code.label"
                                            :rules="updateDeviceFormScheme.fields.code.rules"
                                            :required="updateDeviceFormScheme.fields.code.required"
                                    />
                                </v-flex>

                                <v-flex
                                        xs12
                                >
                                    <v-text-field
                                            v-model="updateDeviceFormScheme.model.description"
                                            :label="updateDeviceFormScheme.fields.description.label"
                                            :rules="updateDeviceFormScheme.fields.description.rules"
                                    />
                                </v-flex>

                            </v-layout>
                        </v-container>
                    </v-form>
                </v-card-text>
                <v-card-actions>
                    <v-spacer />
                    <v-btn
                            color="info"
                            @click="clearUpdateDeviceForm()"
                    >
                        返回
                    </v-btn>
                    <v-btn
                            color="success"
                            @click="updateDevice()"
                    >
                        保存
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-layout>
</template>

<script>
    import qs from 'qs'

    const _ = require('lodash/core')
    const uuidv4 = require('uuid/v4')

    export default {
        name: 'UserManager',
        data: () => ({
            toHome: { path: '/' },
            newDeviceDialog: false,
            updateDeviceDialog: false,
            newDeviceFormScheme: {
                valid: true,
                model: {
                    userId:'',
                    username: '',
                    password: '',
                    email: '',
                    phone: '',
                    header: '',
                    userStatus: '',
                    userDescription: ''
                },
                fields: {
                    userId: {
                        type: 'text',
                        label: '用户ID',
                        required: true,
                        rules: [
                            v => !!v || '用户ID必输'
                        ]
                    },
                    username: {
                        type: 'text',
                        label: '用户名',
                        required: true,
                        rules: [
                            v => !!v || '用户名必输'
                        ]
                    },
                    password: {
                        type: 'text',
                        label: '密码',
                        hint: '密码',
                        required: true,
                        rules: [
                            v => !!v || '密码必输'
                        ]
                    },
                    email: {
                        type: 'text',
                        label: '邮箱地址',
                        rules: [
                            v => v.length < 256 || '邮箱地址必输'
                        ]
                    },
                    phone: {
                        type: 'text',
                        label: '电话号码',
                        rules: [
                            v => v.length < 256 || '电话号码必输'
                        ]
                    },
                    header: {
                        type: 'text',
                        label: '用户头像',
                        rules: [
                            v => v.length < 256 || '用户头像必输'
                        ]
                    },
                    userStatus: {
                        type: 'text',
                        label: '状态',
                        rules: [
                            v => v.length < 256 || '状态必输'
                        ]
                    },
                    userDescription: {
                        type: 'text',
                        label: '用户简介',
                        rules: [
                            v => v.length < 256 || '用户简介必输'
                        ]
                    }
                }
            },
            updateDeviceFormScheme: {
                valid: true,
                model: {
                    userId:'',
                    username: '',
                    password: '',
                    email: '',
                    phone: '',
                    header: '',
                    userStatus: '',
                    userDescription: ''
                },
                fields: {
                    userId: {
                        type: 'text',
                        label: '用户ID',
                        required: true,
                        rules: [
                            v => !!v || '用户ID必输'
                        ]
                    },
                    username: {
                        type: 'text',
                        label: '用户名',
                        required: true,
                        rules: [
                            v => !!v || '用户名必输'
                        ]
                    },
                    password: {
                        type: 'text',
                        label: '密码',
                        hint: '密码',
                        required: true,
                        rules: [
                            v => !!v || '密码必输'
                        ]
                    },
                    email: {
                        type: 'text',
                        label: '邮箱地址',
                        rules: [
                            v => v.length < 256 || '邮箱地址必输'
                        ]
                    },
                    phone: {
                        type: 'text',
                        label: '电话号码',
                        rules: [
                            v => v.length < 256 || '电话号码必输'
                        ]
                    },
                    header: {
                        type: 'text',
                        label: '用户头像',
                        rules: [
                            v => v.length < 256 || '用户头像必输'
                        ]
                    },
                    userStatus: {
                        type: 'text',
                        label: '状态',
                        rules: [
                            v => v.length < 256 || '状态必输'
                        ]
                    },
                    userDescription: {
                        type: 'text',
                        label: '用户简介',
                        rules: [
                            v => v.length < 256 || '用户简介必输'
                        ]
                    }
                }
            }
        }),
        computed: {
            productVO: {
                get () {
                    // return _.isEmpty(this.$store.state.productVO) ? {} : this.$store.state.productVO
                    return this.$store.state.productVO
                },
                set (value) {
                    this.$store.commit('setProductVO', value)
                }
            },
            devices: {
                get () {
                    return _.isEmpty(this.$store.state.productVO) ? [] : this.$store.state.productVO.devices
                },
                set (value) {
                    this.productVO = {
                        ...this.productVO,
                        devices: value
                    }
                }
            },
            productId () {
                return this.$store.getters.productId
            },
            toProduct () {
                return { path: `/` }
            }
        },
        created () {
            this.getDevices()
        },
        methods: {
            getDevices () {
                if (true) {
                    this.$store.commit('setGlobalLoading', true)
                    this.$http.post('/user/list').then(res => {
                        if (res.data.code === 0) {
                            this.devices = res.data.data
                        }
                    }).catch().finally(() => setTimeout(() => this.$store.commit('setGlobalLoading', false), 500))
                } else {
                    this.$dialog.notify.error(('您还没有选择产品！即将为您跳转到首页'), {
                        position: 'bottom-right',
                        timeout: 3000
                    })
                    this.$router.push({ path: '/' })
                }
            },
            isEmpty (value) {
                return _.isEmpty(value)
            },
            addDevice () {
                if (this.$refs.newDeviceForm.validate()) {
                    this.$store.commit('setGlobalLoading', true)
                    // this.newDeviceFormScheme.model.productId = this.productId
                    this.$http.post('/user', qs.stringify(this.newDeviceFormScheme.model)).then(res => {
                        this.getDevices()
                        this.clearNewDeviceForm()
                    }).catch(reason => {
                        //
                    }).finally(() => setTimeout(() => this.$store.commit('setGlobalLoading', false), 500))
                }
            },
            updateDeviceButton (device) {
                // 一定要用这种方法赋值，返回的是新的对象，而不会修改原来的对象
                this.updateDeviceFormScheme.model = {
                    ...device
                }
                this.updateDeviceDialog = true
            },
            updateDevice () {
                if (this.updateDeviceFormScheme.model.deviceId) {
                    this.$store.commit('setGlobalLoading', true)
                    this.updateDeviceFormScheme.model.productId = this.productId
                    this.$http.patch(`/user/${this.updateDeviceFormScheme.model.userId}`, qs.stringify(this.updateDeviceFormScheme.model)).then(res => {
                        this.getDevices()
                        this.clearUpdateDeviceForm()
                    }).catch(reason => {
                        //
                    }).finally(() => setTimeout(() => this.$store.commit('setGlobalLoading', false), 500))
                }
            },
            clearNewDeviceForm () {
                this.newDeviceFormScheme.model = {
                    deviceId: '',
                    deviceSecret: '',
                    productId: 0,
                    code: '',
                    name: '',
                    description: '',
                    tags: ''
                }
                this.$refs.newDeviceForm.resetValidation()
                this.newDeviceDialog = false
            },
            clearUpdateDeviceForm () {
                this.updateDeviceFormScheme.model = {
                    deviceId: '',
                    deviceSecret: '',
                    productId: 0,
                    code: '',
                    name: '',
                    description: '',
                    tags: ''
                }
                this.$refs.updateDeviceForm.resetValidation()
                this.updateDeviceDialog = false
            },
            deleteDevice (userId) {
                if (userId) {
                    this.$dialog.confirm({
                        text: '确定要删除这个设备吗？',
                        title: '警告!',
                        persistent: true,
                        waitForResult: true,
                        actions: {
                            false: {
                                flat: false,
                                text: '取消'
                            },
                            true: {
                                color: 'red',
                                text: '确定',
                                flat: false
                            }
                        }
                    }).then(value => {
                        if (value) {
                            this.$store.commit('setGlobalLoading', true)
                            this.$http.delete(`/user/${userId}`).then(() => {
                                this.getDevices()
                            }).finally(() => setTimeout(() => this.$store.commit('setGlobalLoading', false), 500))
                        }
                    })
                }
            },
            generateDeviceSecret (model) {
                model.deviceSecret = uuidv4()
            },
            choiceDevice (userId) {
                this.$store.commit('setDeviceId', userId)
                this.$router.push({ name: 'DEVICE' })
            }
        }
    }
</script>

<style scoped>

</style>
