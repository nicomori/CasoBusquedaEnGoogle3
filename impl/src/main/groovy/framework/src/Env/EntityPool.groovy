package framework.src.Env

import framework.src.Orchestration.UIEmployee
//import zenjob.testautomation.orchestration.UIEmployee

class EntityPool {
    Stack<UIEmployee> employees

    UIEmployee employee

    UIEmployee allocateNewEmployee() {
        employee
    }

    EntityPool() {
        employee = new UIEmployee()
    }
}
