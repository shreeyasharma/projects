﻿using UnityEngine;
using System.Collections;

public class DestroyByBoundary : MonoBehaviour {
	void OnTriggerExit(Collider other)
	{
	Destroy(other.gameObject);
		
	// Use this for initialization

}
}
